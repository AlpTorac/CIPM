/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.vpexplorer.explorer;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * Action for switching back the VPM version. It is realized as a drop down box from which the user
 * can choose the version.
 */
public class SwitchBackVPM extends Action {

    /**
     * Helper that provides the VPM paths and a method to switch back to the selected path.
     */
    public interface SwitchBackVPMHelper {
        /**
         * Provides all available VPM paths of the project.
         * 
         * @return All available VPM paths.
         */
        List<String> getVPMPaths();

        /**
         * Switches the VPM version back to the given VPM path. All older models will be
         * unregistered and deleted.
         * 
         * @param path
         *            The path to switch back to.
         */
        void switchBackToPath(String path);
    }

    /**
     * Rollback action for a specific VPM path. This is used in the selection menu for the user.
     */
    private class VPMRollbackMenuAction extends Action {

        private final String vpmPath;

        public VPMRollbackMenuAction(String vpmPath) {
            super(FilenameUtils.getBaseName(vpmPath));
            this.vpmPath = vpmPath;
        }

        @Override
        public void runWithEvent(Event event) {
            Shell shell = event.widget.getDisplay().getShells()[0];
            boolean confirmed = MessageDialog
                    .openQuestion(
                            shell,
                            "Switch Back VPM Version",
                            String.format(
                                    "You want to switch back to %s, which removes all later versions. Do you want to continue?",
                                    getText()));
            if (confirmed) {
                vpexplorer.getSwitchBackVPMHelper().switchBackToPath(vpmPath);
            }
        }

    }

    private static final String ACTION_TEXT = "Switch Back VPM";
    private final VPExplorer vpexplorer;

    /**
     * Constructs a new switch back action for VPMs.
     * 
     * @param vpexplorer
     *            The VPExplorer to which this action belongs. It also provides the switch back
     *            helper used in this action.
     */
    public SwitchBackVPM(VPExplorer vpexplorer) {
        super(ACTION_TEXT, AS_DROP_DOWN_MENU);
        this.vpexplorer = vpexplorer;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO);
    }

    @Override
    public IMenuCreator getMenuCreator() {
        IMenuCreator menuCreator = new IMenuCreator() {

            @Override
            public void dispose() {
                // TODO Auto-generated method stub
            }

            @Override
            public Menu getMenu(Control parent) {
                return createMenuManager().createContextMenu(parent);
            }

            @Override
            public Menu getMenu(Menu parent) {
                return null;
            }

            private MenuManager createMenuManager() {
                MenuManager manager = new MenuManager();

                manager.addMenuListener(new IMenuListener() {
                    @Override
                    public void menuAboutToShow(IMenuManager manager) {
                        if (vpexplorer.getSwitchBackVPMHelper() != null
                                && vpexplorer.getSwitchBackVPMHelper().getVPMPaths().iterator().hasNext()) {
                            for (String id : Lists.reverse(vpexplorer.getSwitchBackVPMHelper().getVPMPaths())) {
                                manager.add(new VPMRollbackMenuAction(id));
                            }
                        }
                    }
                });

                return manager;
            }
        };

        return menuCreator;
    }

}
