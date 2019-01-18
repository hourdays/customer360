package com.ml.mlu;

import java.io.File;
import java.io.IOException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.datamovement.DataMovementManager;
import com.marklogic.client.datamovement.WriteBatch;
import com.marklogic.client.datamovement.WriteBatchListener;
import com.marklogic.client.datamovement.WriteBatcher;
import com.marklogic.client.datamovement.WriteFailureListener;
import com.marklogic.client.document.ServerTransform;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.FileHandle;
import com.marklogic.client.io.Format;
import com.ml.mlu.Utils;
import com.ml.mlu.Utils.ExampleProperties;


public class LoadAndTransform {
	static final private String URI_PREFIX = "/marketing/";
	static final private String[] FILENAMES = {"contact-25.xml", "contact-26.xml", "contact-27.xml"};
	static final private String[] COLLECTIONS = {"canonical", "dmsdk_transformed"};
	static final private String TRANSFORM = "envelope";

	public static void main(String[] args) throws IOException {
		run(Utils.loadProperties());
	}
	
	public static void run(ExampleProperties props) throws IOException {
		System.out.println("settings: " + props.host + ":" + props.port);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		// connect the client
		DatabaseClient client = Utils.getDbClient(props);

		loadData(client, props);

//		tearDownExample(client);

		// release the client
		client.release();
	}
	
	// set up by writing the document content used in the example query
	public static void loadData(DatabaseClient client, ExampleProperties props)
		throws IOException
	{
		DocumentMetadataHandle metadata = 
	      new DocumentMetadataHandle().withCollections(COLLECTIONS);
		
		DataMovementManager dmm = client.newDataMovementManager();
		WriteBatcher batcher = dmm.newWriteBatcher()
				// Add the transform
				.withTransform(new ServerTransform(TRANSFORM))
				.onBatchSuccess(new WriteBatchListener() {
					public void processEvent(WriteBatch batch) {
						System.out.println("batch # " + batch.getJobBatchNumber()
							+ " - files loaded : " + batch.getJobWritesSoFar());
					}
				})
				.onBatchFailure(new WriteFailureListener() {
					public void processFailure(WriteBatch batch, Throwable e) {
						System.out.println("FAILED: batch # " + batch.getJobBatchNumber()
							+ ", so far: " + e);
						e.printStackTrace();
					}
				});
		dmm.startJob(batcher);
		
		for (String filename: FILENAMES) {
			FileHandle fh = new FileHandle(new File(props.dataPath + filename));
			
			// Set the file format for transform code
			fh.withFormat(Format.XML);
			
			// Add the metadata for the collections
			batcher.add(URI_PREFIX + filename, metadata, fh);
		}
		
		batcher.flushAndWait();
		System.out.println("Finished loading docs");
		
		dmm.stopJob(batcher);
		dmm.release();
	}

	public static void tearDownExample(DatabaseClient client) {
		XMLDocumentManager docMgr = client.newXMLDocumentManager();

		for (String filename: FILENAMES) {
			docMgr.delete(URI_PREFIX + filename);
		}
	}

}
