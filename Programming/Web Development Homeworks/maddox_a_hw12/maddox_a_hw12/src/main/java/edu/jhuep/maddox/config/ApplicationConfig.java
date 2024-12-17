/**
 * @author Andrew Maddox
 * @since Apr 20, 2024
 */
package edu.jhuep.maddox.config;

import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * The structure of this class is based on the lecture 12 material at https://www.jhuep.com/~spiegel/en605681/REST/RestServices.html
 * from Principles of Enterprise Web Development. JHU 2024. Written by Professor Richard Spiegel
 */
@javax.ws.rs.ApplicationPath("bhc")

public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(edu.jhuep.maddox.endpoint.Endpoint.class);
	}

}
