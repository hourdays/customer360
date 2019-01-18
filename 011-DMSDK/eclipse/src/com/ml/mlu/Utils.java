package com.ml.mlu;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	public static DatabaseClient getDbClient(ExampleProperties props) {
		return DatabaseClientFactory.newClient(
			props.host, props.port,
			new DatabaseClientFactory.DigestAuthContext(props.writerUser, props.writerPassword)
		);
	}

	/**
	 * ExampleProperties represents the configuration for the examples.
	 */
	static public class ExampleProperties {
		public String host;
		public int    port = -1;
		public String adminUser;
		public String adminPassword;
		public String readerUser;
		public String readerPassword;
		public String writerUser;
		public String writerPassword;
		public String authType;
		public String dataPath;
		public ExampleProperties(Properties props) {
			super();
			host           = props.getProperty("example.host");
			port           = Integer.parseInt(props.getProperty("example.port"));
			adminUser      = props.getProperty("example.admin_user");
			adminPassword  = props.getProperty("example.admin_password");
			readerUser     = props.getProperty("example.reader_user");
			readerPassword = props.getProperty("example.reader_password");
			writerUser     = props.getProperty("example.writer_user");
			writerPassword = props.getProperty("example.writer_password");
			authType       = props.getProperty("example.authentication_type").toUpperCase();
			dataPath       = props.getProperty("example.data_path");
		}
	}

	/**
	 * Read the configuration properties for the example from the file
	 * Example.properties.
	 * @return	the configuration object
	 * @throws IOException if one occurs while loading the properties
	 */
	public static ExampleProperties loadProperties() throws IOException {
		String propsName = "Example.properties";

		InputStream propsStream = openStream(propsName);
		if (propsStream == null) {
			throw new IOException("Could not read properties "+propsName);
		}

		Properties props = new Properties();
		props.load(propsStream);

		return new ExampleProperties(props);
	}

	/**
	 * Read a resource for an example.
	 * @param fileName	the name of the resource
	 * @return	an input stream for the resource
	 * @throws IOException if one occurs opening the stream
	 */
	public static InputStream openStream(String fileName) throws IOException {
		return Utils.class.getClassLoader().getResourceAsStream(fileName);
	}
}
