package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.time.LocalDateTime;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.StopWatch;

/**
 * TODO Revise commentary
 * 
 * A class for taking time measurements using
 * {@link org.apache.commons.lang.time.StopWatch}
 * 
 * @author Alp Torac Genc
 */
public class StopwatchStrategy implements ITimeMeasuringStrategy {

	/**
	 * The time when time measurement has begun
	 */
	private LocalDateTime startTime;
	/**
	 * The time when time measurement has ended
	 */
	private LocalDateTime endTime;

	/**
	 * A stack that contains all StopWatch instances that are used during
	 * performance measurement. The reason to use a stack here is, there are cases,
	 * where methods make calls to other methods and their run times overlap. By
	 * suspending the outer method's StopWatch and pushing a new StopWatch onto the
	 * stack, the inner methods' run times can be measured accurately. Then the new
	 * StopWatch can be popped and stopped to get the run time of the inner method.
	 * Finally, the outer method's StopWatch can be resumed to resume the time
	 * measurement.<br>
	 * <br>
	 * All StopWatch are accompanied by an entry, along which they were created.
	 * This way, the StopWatch instances and their corresponding entries are easier
	 * to access.
	 */
	private final Stack<StopWatchEntryPair> watchEntryPairs = new Stack<StopWatchEntryPair>();

	public void startTimeMeasurement(ParserTestTimeMeasurementKey key, ITimeMeasurementTag tag) {
		/*
		 * Suspends the potential outer method's Stopwatch, so that time measurements do
		 * not overlap
		 */
		if (!watchEntryPairs.isEmpty()) {
			var outerMethodPair = watchEntryPairs.peek();
			outerMethodPair.getWatch().suspend();
		}

		var currentMethodWatch = new StopWatch();

		var entry = new TimeMeasurementEntry(key, tag);

		watchEntryPairs.push(new StopWatchEntryPair(currentMethodWatch, entry));
		currentMethodWatch.start();
	}

	public ITimeMeasurementDataStructureEntry stopTimeMeasurement() {
		var currentMethodPair = watchEntryPairs.pop();
		var watch = currentMethodPair.getWatch();
		var entry = currentMethodPair.getEntry();

		watch.stop();
		entry.setTimeUnitCount(watch.getTime());

		/*
		 * Resumes the potential outer method's Stopwatch, which was previously
		 * suspended
		 */
		if (!watchEntryPairs.isEmpty()) {
			watchEntryPairs.peek().getWatch().resume();
		}
		return entry;
	}

	@Override
	public void timeMeasuringFinished() {
		if (this.hasTimeMeasurementStarted() && !this.hasTimeMeasurementFinished()) {
			this.endTime = LocalDateTime.now();
		}
	}

	@Override
	public void timeMeasuringStarted() {
		if (!this.hasTimeMeasurementStarted()) {
			this.startTime = LocalDateTime.now();

			// Reset the end time, since time measuring just started
			this.endTime = null;
		}
	}

	/**
	 * Time measurement is assumed to have started, if
	 * {@link #timeMeasuringStarted()} has been called but
	 * {@link #timeMeasuringFinished()} is not called yet.
	 */
	@Override
	public boolean hasTimeMeasurementStarted() {
		return this.getStartTime() != null && this.getEndTime() == null;
	}

	/**
	 * Time measurement is assumed to have finished, if both
	 * {@link #timeMeasuringStarted()} and {@link #timeMeasuringFinished()} have
	 * been called.
	 */
	@Override
	public boolean hasTimeMeasurementFinished() {
		return this.getStartTime() != null && this.getEndTime() != null;
	}

	/**
	 * Since {@link StopWatch} is used, the used time unit is milliseconds (ms).
	 * 
	 * @return {@link TimeUnit#MILLISECONDS}
	 */
	@Override
	public TimeUnit getTimeUnit() {
		return TimeUnit.MILLISECONDS;
	}

	@Override
	public String getTimeMeasurerDescription() {
		return StopWatch.class.getName();
	}

	@Override
	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	@Override
	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	private class StopWatchEntryPair {
		private final StopWatch watch;
		private final ITimeMeasurementDataStructureEntry entry;

		private StopWatchEntryPair(StopWatch watch, ITimeMeasurementDataStructureEntry entry) {
			this.watch = watch;
			this.entry = entry;
		}

		private StopWatch getWatch() {
			return watch;
		}

		private ITimeMeasurementDataStructureEntry getEntry() {
			return entry;
		}
	}
}
