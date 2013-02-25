/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.splevo.diffing.emfcompare.java2kdmdiff.util.Java2KDMDiffAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffItemProviderAdapterFactory extends Java2KDMDiffAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
     * This keeps track of the root adapter factory that delegates to this adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
     * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
     * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
     * This constructs an instance.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffItemProviderAdapterFactory() {
        supportedTypes.add(IEditingDomainItemProvider.class);
        supportedTypes.add(IStructuredItemContentProvider.class);
        supportedTypes.add(ITreeItemContentProvider.class);
        supportedTypes.add(IItemLabelProvider.class);
        supportedTypes.add(IItemPropertySource.class);
    }

	/**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange} instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected StatementChangeItemProvider statementChangeItemProvider;

	/**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Adapter createStatementChangeAdapter() {
        if (statementChangeItemProvider == null) {
            statementChangeItemProvider = new StatementChangeItemProvider(this);
        }

        return statementChangeItemProvider;
    }

	/**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert} instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ImportInsertItemProvider importInsertItemProvider;

	/**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Adapter createImportInsertAdapter() {
        if (importInsertItemProvider == null) {
            importInsertItemProvider = new ImportInsertItemProvider(this);
        }

        return importInsertItemProvider;
    }

	/**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete} instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ImportDeleteItemProvider importDeleteItemProvider;

	/**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Adapter createImportDeleteAdapter() {
        if (importDeleteItemProvider == null) {
            importDeleteItemProvider = new ImportDeleteItemProvider(this);
        }

        return importDeleteItemProvider;
    }

	/**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ImplementsInterfaceInsertItemProvider implementsInterfaceInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createImplementsInterfaceInsertAdapter() {
        if (implementsInterfaceInsertItemProvider == null) {
            implementsInterfaceInsertItemProvider = new ImplementsInterfaceInsertItemProvider(this);
        }

        return implementsInterfaceInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ImplementsInterfaceDeleteItemProvider implementsInterfaceDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createImplementsInterfaceDeleteAdapter() {
        if (implementsInterfaceDeleteItemProvider == null) {
            implementsInterfaceDeleteItemProvider = new ImplementsInterfaceDeleteItemProvider(this);
        }

        return implementsInterfaceDeleteItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FieldInsertItemProvider fieldInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createFieldInsertAdapter() {
        if (fieldInsertItemProvider == null) {
            fieldInsertItemProvider = new FieldInsertItemProvider(this);
        }

        return fieldInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FieldDeleteItemProvider fieldDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createFieldDeleteAdapter() {
        if (fieldDeleteItemProvider == null) {
            fieldDeleteItemProvider = new FieldDeleteItemProvider(this);
        }

        return fieldDeleteItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassInsertItemProvider classInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createClassInsertAdapter() {
        if (classInsertItemProvider == null) {
            classInsertItemProvider = new ClassInsertItemProvider(this);
        }

        return classInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassDeleteItemProvider classDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createClassDeleteAdapter() {
        if (classDeleteItemProvider == null) {
            classDeleteItemProvider = new ClassDeleteItemProvider(this);
        }

        return classDeleteItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PackageInsertItemProvider packageInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createPackageInsertAdapter() {
        if (packageInsertItemProvider == null) {
            packageInsertItemProvider = new PackageInsertItemProvider(this);
        }

        return packageInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PackageDeleteItemProvider packageDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createPackageDeleteAdapter() {
        if (packageDeleteItemProvider == null) {
            packageDeleteItemProvider = new PackageDeleteItemProvider(this);
        }

        return packageDeleteItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MethodInsertItemProvider methodInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createMethodInsertAdapter() {
        if (methodInsertItemProvider == null) {
            methodInsertItemProvider = new MethodInsertItemProvider(this);
        }

        return methodInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MethodDeleteItemProvider methodDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createMethodDeleteAdapter() {
        if (methodDeleteItemProvider == null) {
            methodDeleteItemProvider = new MethodDeleteItemProvider(this);
        }

        return methodDeleteItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementInsert} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StatementInsertItemProvider statementInsertItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementInsert}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createStatementInsertAdapter() {
        if (statementInsertItemProvider == null) {
            statementInsertItemProvider = new StatementInsertItemProvider(this);
        }

        return statementInsertItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementDelete} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StatementDeleteItemProvider statementDeleteItemProvider;

    /**
     * This creates an adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementDelete}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createStatementDeleteAdapter() {
        if (statementDeleteItemProvider == null) {
            statementDeleteItemProvider = new StatementDeleteItemProvider(this);
        }

        return statementDeleteItemProvider;
    }

    /**
     * This returns the root adapter factory that contains this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComposeableAdapterFactory getRootAdapterFactory() {
        return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
    }

	/**
     * This sets the composed adapter factory that contains this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
        this.parentAdapterFactory = parentAdapterFactory;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object type) {
        return supportedTypes.contains(type) || super.isFactoryForType(type);
    }

	/**
     * This implementation substitutes the factory itself as the key for the adapter.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
        return super.adapt(notifier, this);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object adapt(Object object, Object type) {
        if (isFactoryForType(type)) {
            Object adapter = super.adapt(object, type);
            if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
                return adapter;
            }
        }

        return null;
    }

	/**
     * This adds a listener.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void addListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.addListener(notifyChangedListener);
    }

	/**
     * This removes a listener.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.removeListener(notifyChangedListener);
    }

	/**
     * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void fireNotifyChanged(Notification notification) {
        changeNotifier.fireNotifyChanged(notification);

        if (parentAdapterFactory != null) {
            parentAdapterFactory.fireNotifyChanged(notification);
        }
    }

	/**
     * This disposes all of the item providers created by this factory. 
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void dispose() {
        if (statementChangeItemProvider != null) statementChangeItemProvider.dispose();
        if (importInsertItemProvider != null) importInsertItemProvider.dispose();
        if (importDeleteItemProvider != null) importDeleteItemProvider.dispose();
        if (implementsInterfaceInsertItemProvider != null) implementsInterfaceInsertItemProvider.dispose();
        if (implementsInterfaceDeleteItemProvider != null) implementsInterfaceDeleteItemProvider.dispose();
        if (fieldInsertItemProvider != null) fieldInsertItemProvider.dispose();
        if (fieldDeleteItemProvider != null) fieldDeleteItemProvider.dispose();
        if (classInsertItemProvider != null) classInsertItemProvider.dispose();
        if (classDeleteItemProvider != null) classDeleteItemProvider.dispose();
        if (packageInsertItemProvider != null) packageInsertItemProvider.dispose();
        if (packageDeleteItemProvider != null) packageDeleteItemProvider.dispose();
        if (methodInsertItemProvider != null) methodInsertItemProvider.dispose();
        if (methodDeleteItemProvider != null) methodDeleteItemProvider.dispose();
        if (statementInsertItemProvider != null) statementInsertItemProvider.dispose();
        if (statementDeleteItemProvider != null) statementDeleteItemProvider.dispose();
    }

}
