/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package coveragepac;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.extensions.members.ConstructorExtension;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Utility class to handle JaMoPP elements.
 */
public final class JaMoPPElementUtil {

	/** Disable constructor for utility class. */
	private JaMoPPElementUtil() {
	}

	/**
	 * Get the first container that is not of any given type.
	 * 
	 * @param element               The element to get an appropriate container for.
	 * @param ignoredContainerTypes A set of types that should be skipped.
	 * @return The first matching container, which can be null as well.
	 */
	public static EObject getFirstContainerNotOfGivenType(final Commentable element,
			final Class<?>... ignoredContainerTypes) {
		Predicate<EObject> matchingPredicate = new Predicate<EObject>() {
			@Override
			public boolean apply(EObject container) {
				for (Class<?> clazz : ignoredContainerTypes) {
					if (clazz.isAssignableFrom(container.getClass())) {
						return false;
					}
				}
				return true;
			}
		};
		return getTransitiveContainerForPredicate(element, matchingPredicate);
	}

	/**
	 * Get the first container of an element which matches the given predicate.
	 * 
	 * @param element   The element to get an appropriate container for.
	 * @param predicate The predicate that matches a valid container.
	 * @return The first matching container, which can be null as well.
	 */
	private static EObject getTransitiveContainerForPredicate(Commentable element, Predicate<EObject> predicate) {
		EObject container = element.eContainer();
		while (container != null && !predicate.apply(container)) {
			container = container.eContainer();
		}
		return container;
	}

	/**
	 * Get the position of a statement in its container. If the container is not a
	 * {@link StatementListContainer} the method will always return -1.
	 * 
	 * @param statement The statement to check the position of.
	 * @return The position in the container's statement list.
	 */
	public static int getPositionInContainer(Statement statement) {

		if (statement.eContainer() instanceof StatementListContainer) {
			StatementListContainer container = (StatementListContainer) statement.eContainer();
			var sts = container.getStatements();
			return sts != null ? sts.indexOf(statement) : -1;
		}

		return -1;
	}
}
