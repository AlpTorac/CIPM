package cipm.consistency.fitests.similarity.base.dummy;

import java.util.ArrayList;
import java.util.List;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public class DummySimilarityToolbox implements ISimilarityToolbox {
	private List<RequestHandlerPairEntry> pairs;
	private List<HandleHistoryEntry> history;
	
	public DummySimilarityToolbox() {
		this.init();
	}
	
	public void init() {
		this.pairs = new ArrayList<RequestHandlerPairEntry>();
		this.history = new ArrayList<HandleHistoryEntry>();
	}
	
	@Override
	public void addRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass, ISimilarityRequestHandler srh) {
		var entry = new RequestHandlerPairEntry();
		
		entry.setReqCls(reqClass);
		entry.setHandler(srh);
		
		this.pairs.add(entry);
	}

	@Override
	public void removeRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass) {
		this.pairs.removeIf((e) -> e.getReqCls().equals(reqClass));
	}

	@Override
	public void clearRequestHandlerPairs() {
		this.pairs.clear();
	}
	
	protected RequestHandlerPairEntry[] getPairsFor(Class<? extends ISimilarityRequest> reqClass) {
		return this.pairs.stream()
				.filter((e) -> e.getReqCls().equals(reqClass))
				.toArray(RequestHandlerPairEntry[]::new);
	}

	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var pairs = this.getPairsFor(req.getClass());
		var count = pairs.length;
		
		var results = new Object[count];
		
		for (int i = 0; i < count; i++) {
			var he = new HandleHistoryEntry();
			
			var handler = pairs[i].getHandler();
			var output = handler.handleSimilarityRequest(req);
			results[i] = output;
			
			he.setReq(req);
			he.setHandler(handler);
			he.setHandled(handler.canHandleSimilarityRequest(req));
			he.setOutput(output);
			this.history.add(he);
		}
		
		var reducedResult = this.reduceResults(results);
		
		return reducedResult != null ? reducedResult : results;
	}
	
	public Boolean reduceResults(Object output) {
		if (output instanceof Boolean) return (Boolean) output;
		
		if (output.getClass().isArray()) {
			var resultArr = (Object[]) output;
			if (resultArr.length == 0) {
				return Boolean.FALSE;
			} else {
				var result = true;
				
				for (var res : resultArr) {
					if (!(res instanceof Boolean)) {
						return null;
					} else {
						result = result && ((Boolean) res).booleanValue();
					}
				}
				
				return result;
			}
		}
		
		return null;
	}
	
	public List<HandleHistoryEntry> getHandlingHistory() {
		return this.history.subList(0, this.history.size());
	}
	
	public class HandleHistoryEntry {
		private ISimilarityRequest req;
		private ISimilarityRequestHandler handler;
		private Object output;
		private boolean isHandled;
		
		protected HandleHistoryEntry() {}

		public ISimilarityRequest getReq() {
			return req;
		}

		public void setReq(ISimilarityRequest req) {
			this.req = req;
		}

		public ISimilarityRequestHandler getHandler() {
			return handler;
		}

		public void setHandler(ISimilarityRequestHandler handler) {
			this.handler = handler;
		}

		public Object getOutput() {
			return output;
		}

		public void setOutput(Object output) {
			this.output = output;
		}

		public boolean isHandled() {
			return isHandled;
		}

		public void setHandled(boolean isHandled) {
			this.isHandled = isHandled;
		}
	}

	protected class RequestHandlerPairEntry {
		private Class<? extends ISimilarityRequest> reqCls;
		private ISimilarityRequestHandler handler;
		
		public Class<? extends ISimilarityRequest> getReqCls() {
			return reqCls;
		}
		public void setReqCls(Class<? extends ISimilarityRequest> reqCls) {
			this.reqCls = reqCls;
		}
		public ISimilarityRequestHandler getHandler() {
			return handler;
		}
		public void setHandler(ISimilarityRequestHandler handler) {
			this.handler = handler;
		}
	}

	@Override
	public boolean canHandleSimilarityRequest(Class<? extends ISimilarityRequest> reqClass) {
		return this.getPairsFor(reqClass).length > 0;
	}
}
