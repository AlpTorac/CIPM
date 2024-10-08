package org.splevo.jamopp.diffing.similarity.switches;

import org.apache.log4j.Level;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypedElement;
import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.ILoggableJavaSwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

import com.google.common.base.Strings;

/**
 * Similarity decisions for the member elements.
 */
public class MembersSimilaritySwitch extends MembersSwitch<Boolean>
		implements ILoggableJavaSwitch, IJavaSimilarityPositionInnerSwitch {
	private IJavaSimilaritySwitch similaritySwitch;
	private boolean checkStatementPosition;

	@Override
	public ISimilarityRequestHandler getSimilarityRequestHandler() {
		return this.similaritySwitch;
	}

	@Override
	public boolean shouldCheckStatementPosition() {
		return this.checkStatementPosition;
	}

	@Override
	public IJavaSimilaritySwitch getContainingSwitch() {
		return this.similaritySwitch;
	}

	public MembersSimilaritySwitch(IJavaSimilaritySwitch similaritySwitch, boolean checkStatementPosition) {
		this.similaritySwitch = similaritySwitch;
		this.checkStatementPosition = checkStatementPosition;
	}

	/**
	 * Check abstract method declaration similarity. Similarity is checked by
	 * <ul>
	 * <li>name</li>
	 * <li>parameter list size</li>
	 * <li>parameter types</li>
	 * <li>name</li>
	 * <li>container for
	 * <ul>
	 * <li>AbstractTypeDeclaration</li>
	 * <li>AnonymousClassDeclaration</li>
	 * <li>Model</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * The container must be checked to check similarity for referenced methods.
	 * 
	 * 
	 * @param method1 The abstract method declaration to compare with the compare
	 *                element.
	 * @return True/False if the abstract method declarations are similar or not.
	 */
	@Override
	public Boolean caseMethod(Method method1) {
		this.logMessage("caseMethod");

		Method method2 = (Method) this.getCompareElement();

		var name1 = Strings.nullToEmpty(method1.getName());
		var name2 = Strings.nullToEmpty(method2.getName());

		// if methods have different names they are not similar.
		if (!name1.equals(name2)) {
			return Boolean.FALSE;
		}

		var params1 = method1.getParameters();
		var params2 = method2.getParameters();

		// Null check to avoid NullPointerExceptions
		if (params1 == null ^ params2 == null) {
			return Boolean.FALSE;
		} else if (params1 != null && params2 != null) {
			if (params1.size() != params2.size()) {
				return Boolean.FALSE;
			}

			for (int i = 0; i < params1.size(); i++) {
				Parameter param1 = params1.get(i);
				Parameter param2 = params2.get(i);

				var tref1 = param1.getTypeReference();
				var tref2 = param2.getTypeReference();

				if (tref1 == null ^ tref2 == null) {
					return Boolean.FALSE;
				} else if (tref1 != null && tref2 != null) {
					Type type1 = tref1.getTarget();
					Type type2 = tref2.getTarget();
					Boolean typeSimilarity = this.isSimilar(type1, type2);
					if (typeSimilarity == Boolean.FALSE) {
						return Boolean.FALSE;
					}
					if (tref1.getArrayDimension() != tref2.getArrayDimension()) {
						return Boolean.FALSE;
					}
				}
			}
		}

		/*
		 * ************************************** methods as members of regular classes
		 */
		if (method1.getContainingConcreteClassifier() != null) {
			ConcreteClassifier type1 = method1.getContainingConcreteClassifier();
			ConcreteClassifier type2 = method2.getContainingConcreteClassifier();
			return this.isSimilar(type1, type2);
		}

		/*
		 * ************************************** methods as members of anonymous
		 * classes
		 */
		if (method1.getContainingAnonymousClass() != null) {
			AnonymousClass type1 = method1.getContainingAnonymousClass();
			AnonymousClass type2 = method2.getContainingAnonymousClass();
			Boolean typeSimilarity = this.isSimilar(type1, type2);
			if (typeSimilarity != null) {
				return typeSimilarity;
			}
		}

		var containerString = method1.eContainer() == null ? "" : method1.eContainer().toString();

		this.logMessage("MethodDeclaration in unknown container: " + name1 + " : " + containerString, Level.WARN);
		return null;
	}

	/**
	 * Check constuctor declaration similarity. Similarity is checked by
	 * <ul>
	 * <li>name</li>
	 * <li>parameter list size</li>
	 * <li>parameter types</li>
	 * <li>name</li>
	 * <li>container for
	 * <ul>
	 * <li>AbstractTypeDeclaration</li>
	 * <li>AnonymousClassDeclaration</li>
	 * <li>Model</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * The container must be checked to check similarity for referenced methods.
	 * 
	 * 
	 * @param constructor1 The abstract method declaration to compare with the
	 *                     compare element.
	 * @return True/False if the abstract method declarations are similar or not.
	 */
	@Override
	public Boolean caseConstructor(Constructor constructor1) {
		this.logMessage("caseConstructor");

		Constructor constructor2 = (Constructor) this.getCompareElement();

		var name1 = Strings.nullToEmpty(constructor1.getName());
		var name2 = Strings.nullToEmpty(constructor2.getName());

		// if methods have different names they are not similar.
		if (!name1.equals(name2)) {
			return Boolean.FALSE;
		}

		EList<Parameter> params1 = constructor1.getParameters();
		EList<Parameter> params2 = constructor2.getParameters();
		Boolean parameterSimilarity = this.areSimilar(params1, params2);
		if (parameterSimilarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		/*
		 * ************************************** methods as members of regular classes
		 */
		if (constructor1.getContainingConcreteClassifier() != null) {
			ConcreteClassifier type1 = constructor1.getContainingConcreteClassifier();
			ConcreteClassifier type2 = constructor2.getContainingConcreteClassifier();
			return this.isSimilar(type1, type2);
		}

		/*
		 * ************************************** methods as members of anonymous
		 * classes
		 */
		if (constructor1.getContainingAnonymousClass() != null) {
			AnonymousClass type1 = constructor1.getContainingAnonymousClass();
			AnonymousClass type2 = constructor2.getContainingAnonymousClass();
			Boolean typeSimilarity = this.isSimilar(type1, type2);
			if (typeSimilarity != null) {
				return typeSimilarity;
			}
		}

		var containerString = constructor1.eContainer() == null ? "" : constructor1.eContainer().toString();

		this.logMessage("ConstructorDeclaration in unknown container: " + name1 + " : " + containerString, Level.WARN);
		return null;
	}

	@Override
	public Boolean caseEnumConstant(EnumConstant const1) {
		this.logMessage("caseEnumConstant");

		EnumConstant const2 = (EnumConstant) this.getCompareElement();
		String name1 = Strings.nullToEmpty(const1.getName());
		String name2 = Strings.nullToEmpty(const2.getName());
		return (name1.equals(name2));
	}

	@Override
	public Boolean caseMember(Member member1) {
		this.logMessage("caseMember");

		Member member2 = (Member) this.getCompareElement();
		String name1 = Strings.nullToEmpty(member1.getName());
		String name2 = Strings.nullToEmpty(member2.getName());
		return (name1.equals(name2));
	}

	/**
	 * TODO Review this method to make sure it is correct.
	 * 
	 * <i><b>This method was added later, because comparing improperly
	 * initialised additional fields could result in null otherwise.</b></i>
	 * <br><br>
	 * 
	 * Additional fields are considered similar, if:
	 * <ul>
	 * <li> Their names are equal,
	 * <li> Their types are similar,
	 * <li> Types of their containers are similar,
	 * <li> Types of containers of their containers are similar.
	 * </ul>
	 */
	@Override
	public Boolean caseAdditionalField(AdditionalField additionalField1) {
		this.logMessage("caseAdditionalField");
		
		AdditionalField additionalField2 = (AdditionalField) this.getCompareElement();
		String name1 = Strings.nullToEmpty(additionalField1.getName());
		String name2 = Strings.nullToEmpty(additionalField2.getName());
		
		if (!name1.equals(name2)) {
			return Boolean.FALSE;
		}
		
		var type1 = additionalField1.getTypeReference();
		var type2 = additionalField2.getTypeReference();
		
		// Compare additional field types
		// Account for similarity result being null
		if (this.isSimilar(type1, type2) != Boolean.TRUE) {
			return Boolean.FALSE;
		}
		
		var container1 = additionalField1.eContainer();
		var container2 = additionalField2.eContainer();
		
		// Null check to avoid null pointer exceptions
		if (container1 != null && container2 != null) {
			var castedCon1 = (TypedElement) container1;
			var castedCon2 = (TypedElement) container2;

			var conType1 = castedCon1.getTypeReference();
			var conType2 = castedCon2.getTypeReference();
			
			// Compare container types
			// Account for similarity result being null
			if (this.isSimilar(conType1, conType2) != Boolean.TRUE) {
				return Boolean.FALSE;
			}
			
			var containerOfCon1 = castedCon1.eContainer();
			var containerOfCon2 = castedCon2.eContainer();
			
			// Null check to avoid null pointer exceptions
			if (containerOfCon1 != null && containerOfCon2 != null) {
				var castedConOfCon1 = (TypedElement) containerOfCon1;
				var castedConOfCon2 = (TypedElement) containerOfCon2;
				
				var conOfConType1 = castedConOfCon1.getTypeReference();
				var conOfConType2 = castedConOfCon2.getTypeReference();
				
				// Compare types of container of container
				// Account for similarity result being null
				if (this.isSimilar(conOfConType1, conOfConType2) != Boolean.TRUE) {
					return Boolean.FALSE;
				}
				
			} else if (containerOfCon1 == null ^ containerOfCon2 == null) {
				return Boolean.FALSE;
			}
			
		} else if (container1 == null ^ container2 == null) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}