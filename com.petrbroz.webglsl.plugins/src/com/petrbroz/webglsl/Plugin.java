package com.petrbroz.webglsl;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.petrbroz.webglsl.editors.ColorManager;
import com.petrbroz.webglsl.editors.TokenManager;
import com.petrbroz.webglsl.preferences.PreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class Plugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.petrbroz.webglsl"; //$NON-NLS-1$

	// The shared instance
	private static Plugin plugin;

	/**
	 * The constructor
	 */
	public Plugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		for (int i = 0; i < PreferenceConstants.PREFERENCES.length; i++) {
			Token token = TokenManager
					.getToken(PreferenceConstants.PREFERENCES[i][0]);
			RGB rgb = PreferenceConverter.getColor(getPreferenceStore(),
					PreferenceConstants.PREFERENCES[i][1]);
			int style = getPreferenceStore().getInt(
					PreferenceConstants.PREFERENCES[i][2]);
			token.setData(new TextAttribute(ColorManager.getColor(rgb), null,
					style));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Plugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
