

public class MyClass {
	
	
	public static void main(String args[]){
		package beproject.mainjob_0_1;
		
		import routines.Numeric;
		import routines.DataOperation;
		import routines.TalendDataGenerator;
		import routines.TalendString;
		import routines.StringHandling;
		import routines.Relational;
		import routines.TalendDate;
		import routines.Mathematical;
		import routines.system.*;
		import routines.system.api.*;
		import java.text.ParseException;
		import java.text.SimpleDateFormat;
		import java.util.Date;
		import java.util.List;
		import java.math.BigDecimal;
		import java.io.ByteArrayOutputStream;
		import java.io.ByteArrayInputStream;
		import java.io.DataInputStream;
		import java.io.DataOutputStream;
		import java.io.ObjectOutputStream;
		import java.io.ObjectInputStream;
		import java.io.IOException;
		import java.util.Comparator;
		
		@SuppressWarnings("unused")
		/**
		 * Job: Mainjob Purpose: <br>
		 * Description:  <br>
		 * @author user@talend.com
		 * @version 6.0.1.20150908_1633
		 * @status 
		 */
		public class Mainjob implements TalendJob {
		
			public final Object obj = new Object();
		
			// for transmiting parameters purpose
			private Object valueObject = null;
		
			public Object getValueObject() {
				return this.valueObject;
			}
		
			public void setValueObject(Object valueObject) {
				this.valueObject = valueObject;
			}
		
			private final static String defaultCharset = java.nio.charset.Charset
					.defaultCharset().name();
		
			private final static String utf8Charset = "UTF-8";
		
			// create and load default properties
			private java.util.Properties defaultProps = new java.util.Properties();
		
			// create application properties with default
			public class ContextProperties extends java.util.Properties {
		
				private static final long serialVersionUID = 1L;
		
				public ContextProperties(java.util.Properties properties) {
					super(properties);
				}
		
				public ContextProperties() {
					super();
				}
		
				public void synchronizeContext() {
		
					if (host != null) {
		
						this.setProperty("host", host.toString());
		
					}
		
					if (port != null) {
		
						this.setProperty("port", port.toString());
		
					}
		
					if (username != null) {
		
						this.setProperty("username", username.toString());
		
					}
		
					if (password != null) {
		
						this.setProperty("password", password.toString());
		
					}
		
					if (remoteDir != null) {
		
						this.setProperty("remoteDir", remoteDir.toString());
		
					}
		
					if (localDir != null) {
		
						this.setProperty("localDir", localDir.toString());
		
					}
		
				}
		
				public String host;
		
				public String getHost() {
					return this.host;
				}
		
				public Integer port;
		
				public Integer getPort() {
					return this.port;
				}
		
				public String username;
		
				public String getUsername() {
					return this.username;
				}
		
				public String password;
		
				public String getPassword() {
					return this.password;
				}
		
				public String remoteDir;
		
				public String getRemoteDir() {
					return this.remoteDir;
				}
		
				public String localDir;
		
				public String getLocalDir() {
					return this.localDir;
				}
			}
		
			private ContextProperties context = new ContextProperties();
		
			public ContextProperties getContext() {
				return this.context;
			}
		
			private final String jobVersion = "0.1";
			private final String jobName = "Mainjob";
			private final String projectName = "BEPROJECT";
			public Integer errorCode = null;
			private String currentComponent = "";
		
			private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
			private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
		
			private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
			private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
			private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
			public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
		
			public boolean isExportedAsOSGI = false;
		
			// OSGi DataSource
			private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
		
			public void setDataSources(
					java.util.Map<String, javax.sql.DataSource> dataSources) {
				java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
				for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
						.entrySet()) {
					talendDataSources.put(
							dataSourceEntry.getKey(),
							new routines.system.TalendDataSource(dataSourceEntry
									.getValue()));
				}
				globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
			}
		
			LogCatcherUtils tLogCatcher_1 = new LogCatcherUtils();
		
			private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
					new java.io.BufferedOutputStream(baos));
		
			public String getExceptionStackTrace() {
				if ("failure".equals(this.getStatus())) {
					errorMessagePS.flush();
					return baos.toString();
				}
				return null;
			}
		
			private Exception exception;
		
			public Exception getException() {
				if ("failure".equals(this.getStatus())) {
					return this.exception;
				}
				return null;
			}
		
			private class TalendException extends Exception {
		
				private static final long serialVersionUID = 1L;
		
				private java.util.Map<String, Object> globalMap = null;
				private Exception e = null;
				private String currentComponent = null;
				private String virtualComponentName = null;
		
				public void setVirtualComponentName(String virtualComponentName) {
					this.virtualComponentName = virtualComponentName;
				}
		
				private TalendException(Exception e, String errorComponent,
						final java.util.Map<String, Object> globalMap) {
					this.currentComponent = errorComponent;
					this.globalMap = globalMap;
					this.e = e;
				}
		
				public Exception getException() {
					return this.e;
				}
		
				public String getCurrentComponent() {
					return this.currentComponent;
				}
		
				public String getExceptionCauseMessage(Exception e) {
					Throwable cause = e;
					String message = null;
					int i = 10;
					while (null != cause && 0 < i--) {
						message = cause.getMessage();
						if (null == message) {
							cause = cause.getCause();
						} else {
							break;
						}
					}
					if (null == message) {
						message = e.getClass().getName();
					}
					return message;
				}
		
				@Override
				public void printStackTrace() {
					if (!(e instanceof TalendException || e instanceof TDieException)) {
						if (virtualComponentName != null
								&& currentComponent.indexOf(virtualComponentName + "_") == 0) {
							globalMap.put(virtualComponentName + "_ERROR_MESSAGE",
									getExceptionCauseMessage(e));
						}
						globalMap.put(currentComponent + "_ERROR_MESSAGE",
								getExceptionCauseMessage(e));
						System.err
								.println("Exception in component " + currentComponent);
					}
					if (!(e instanceof TDieException)) {
						if (e instanceof TalendException) {
							e.printStackTrace();
						} else {
							e.printStackTrace();
							e.printStackTrace(errorMessagePS);
							Mainjob.this.exception = e;
						}
					}
					if (!(e instanceof TalendException)) {
						try {
							for (java.lang.reflect.Method m : this.getClass()
									.getEnclosingClass().getMethods()) {
								if (m.getName().compareTo(currentComponent + "_error") == 0) {
									m.invoke(Mainjob.this, new Object[] { e,
											currentComponent, globalMap });
									break;
								}
							}
		
							if (!(e instanceof TDieException)) {
								tLogCatcher_1.addMessage("Java Exception",
										currentComponent, 6, e.getClass().getName()
												+ ":" + e.getMessage(), 1);
								tLogCatcher_1Process(globalMap);
							}
						} catch (TalendException e) {
							// do nothing
		
						} catch (Exception e) {
							this.e.printStackTrace();
						}
					}
				}
			}
		
			public void tFTPConnection_1_error(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tFTPConnection_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tFTPFileList_1_error(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tFTPFileList_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tFTPGet_1_error(Exception exception, String errorComponent,
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tFTPFileList_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tFileList_1_error(Exception exception, String errorComponent,
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tFileList_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tRunJob_1_error(Exception exception, String errorComponent,
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tFileList_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tLogCatcher_1_error(Exception exception, String errorComponent,
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tBufferOutput_1_error(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				end_Hash.put(errorComponent, System.currentTimeMillis());
		
				status = "failure";
		
				tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
			}
		
			public void tFTPConnection_1_onSubJobError(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
						.currentThread().getId() + "", "FATAL", "",
						exception.getMessage(),
						ResumeUtil.getExceptionStackTrace(exception), "");
		
			}
		
			public void tFTPFileList_1_onSubJobError(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
						.currentThread().getId() + "", "FATAL", "",
						exception.getMessage(),
						ResumeUtil.getExceptionStackTrace(exception), "");
		
			}
		
			public void tFileList_1_onSubJobError(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
						.currentThread().getId() + "", "FATAL", "",
						exception.getMessage(),
						ResumeUtil.getExceptionStackTrace(exception), "");
		
			}
		
			public void tLogCatcher_1_onSubJobError(Exception exception,
					String errorComponent, final java.util.Map<String, Object> globalMap)
					throws TalendException {
		
				resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
						.currentThread().getId() + "", "FATAL", "",
						exception.getMessage(),
						ResumeUtil.getExceptionStackTrace(exception), "");
		
			}
		
			public void tFTPConnection_1Process(
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
				globalMap.put("tFTPConnection_1_SUBPROCESS_STATE", 0);
		
				final boolean execStat = this.execStat;
		
				String iterateId = "";
		
				String currentComponent = "";
				java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();
		
				try {
		
					String currentMethodName = new java.lang.Exception()
							.getStackTrace()[0].getMethodName();
					boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
					if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																							// the
																							// resume
						globalResumeTicket = true;
		
						/**
						 * [tFTPConnection_1 begin ] start
						 */
		
						ok_Hash.put("tFTPConnection_1", false);
						start_Hash.put("tFTPConnection_1", System.currentTimeMillis());
		
						currentComponent = "tFTPConnection_1";
		
						int tos_count_tFTPConnection_1 = 0;
		
						com.enterprisedt.net.ftp.FTPClient ftp_tFTPConnection_1 = new com.enterprisedt.net.ftp.FTPClient();
						ftp_tFTPConnection_1.setRemoteHost(context.host);
						ftp_tFTPConnection_1.setRemotePort(context.port);
		
						ftp_tFTPConnection_1
								.setConnectMode(com.enterprisedt.net.ftp.FTPConnectMode.PASV);
		
						ftp_tFTPConnection_1.setControlEncoding("ISO-8859-15");
		
						ftp_tFTPConnection_1.connect();
		
						final String decryptedPassword_tFTPConnection_1 = context.password;
		
						ftp_tFTPConnection_1.login(context.username,
								decryptedPassword_tFTPConnection_1);
		
						globalMap.put("conn_tFTPConnection_1", ftp_tFTPConnection_1);
		
						/**
						 * [tFTPConnection_1 begin ] stop
						 */
		
						/**
						 * [tFTPConnection_1 main ] start
						 */
		
						currentComponent = "tFTPConnection_1";
		
						tos_count_tFTPConnection_1++;
		
						/**
						 * [tFTPConnection_1 main ] stop
						 */
		
						/**
						 * [tFTPConnection_1 end ] start
						 */
		
						currentComponent = "tFTPConnection_1";
		
						ok_Hash.put("tFTPConnection_1", true);
						end_Hash.put("tFTPConnection_1", System.currentTimeMillis());
		
						/**
						 * [tFTPConnection_1 end ] stop
						 */
					}// end the resume
		
					if (resumeEntryMethodName == null || globalResumeTicket) {
						resumeUtil
								.addLog("CHECKPOINT",
										"CONNECTION:SUBJOB_OK:tFTPConnection_1:OnSubjobOk",
										"", Thread.currentThread().getId() + "", "",
										"", "", "", "");
					}
		
					tFTPFileList_1Process(globalMap);
		
				} catch (java.lang.Exception e) {
		
					TalendException te = new TalendException(e, currentComponent,
							globalMap);
		
					throw te;
				} catch (java.lang.Error error) {
		
					throw error;
				} finally {
		
					try {
		
						/**
						 * [tFTPConnection_1 finally ] start
						 */
		
						currentComponent = "tFTPConnection_1";
		
						/**
						 * [tFTPConnection_1 finally ] stop
						 */
					} catch (java.lang.Exception e) {
						// ignore
					} catch (java.lang.Error error) {
						// ignore
					}
					resourceMap = null;
				}
		
				globalMap.put("tFTPConnection_1_SUBPROCESS_STATE", 1);
			}
		
			public void tFTPFileList_1Process(
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
				globalMap.put("tFTPFileList_1_SUBPROCESS_STATE", 0);
		
				final boolean execStat = this.execStat;
		
				String iterateId = "";
		
				String currentComponent = "";
				java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();
		
				try {
		
					String currentMethodName = new java.lang.Exception()
							.getStackTrace()[0].getMethodName();
					boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
					if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																							// the
																							// resume
						globalResumeTicket = true;
		
						/**
						 * [tFTPFileList_1 begin ] start
						 */
		
						int NB_ITERATE_tFTPGet_1 = 0; // for statistics
		
						ok_Hash.put("tFTPFileList_1", false);
						start_Hash.put("tFTPFileList_1", System.currentTimeMillis());
		
						currentComponent = "tFTPFileList_1";
		
						int tos_count_tFTPFileList_1 = 0;
		
						java.util.List<String> maskList_tFTPFileList_1 = new java.util.ArrayList<String>();
		
						maskList_tFTPFileList_1.add("Pages.xlsx");
						int nb_file_tFTPFileList_1 = 0;
						com.enterprisedt.net.ftp.FTPClient ftp_tFTPFileList_1 = null;
		
						ftp_tFTPFileList_1 = (com.enterprisedt.net.ftp.FTPClient) globalMap
								.get("conn_tFTPConnection_1");
		
						String remotedir_tFTPFileList_1 = context.remoteDir;
						ftp_tFTPFileList_1.chdir(remotedir_tFTPFileList_1);
						String[] fileList_tFTPFileList_1;
		
						if (false) {
							fileList_tFTPFileList_1 = ftp_tFTPFileList_1
									.dir(null, true);
						} else {
							fileList_tFTPFileList_1 = ftp_tFTPFileList_1.dir(null,
									false);
						}
						List<String> fileListTemp_tFTPFileList_1 = new java.util.ArrayList<String>();
		
						for (String filemask_tFTPFileList_1 : maskList_tFTPFileList_1) {
							java.util.regex.Pattern fileNamePattern_tFTPFileList_1 = java.util.regex.Pattern
									.compile(filemask_tFTPFileList_1.replaceAll("\\.",
											"\\\\.").replaceAll("\\*", ".*"));
		
							for (String filemaskTemp_tFTPFileList_1 : fileList_tFTPFileList_1) {
								if (fileNamePattern_tFTPFileList_1.matcher(
										filemaskTemp_tFTPFileList_1).matches()) {
									fileListTemp_tFTPFileList_1
											.add(filemaskTemp_tFTPFileList_1);
								}
							}
						}
						int i_tFTPFileList_1 = -1;
		
						while (++i_tFTPFileList_1 < fileListTemp_tFTPFileList_1.size()) {
							String currentFileName_tFTPFileList_1 = fileListTemp_tFTPFileList_1
									.get(i_tFTPFileList_1);
							String currentFilePath_tFTPFileList_1 = remotedir_tFTPFileList_1
									+ "/"
									+ fileListTemp_tFTPFileList_1.get(i_tFTPFileList_1);
		
							globalMap.put("tFTPFileList_1_CURRENT_FILE",
									currentFileName_tFTPFileList_1);
							globalMap.put("tFTPFileList_1_CURRENT_FILEPATH",
									currentFilePath_tFTPFileList_1);
							nb_file_tFTPFileList_1++;
		
							/**
							 * [tFTPFileList_1 begin ] stop
							 */
		
							/**
							 * [tFTPFileList_1 main ] start
							 */
		
							currentComponent = "tFTPFileList_1";
		
							tos_count_tFTPFileList_1++;
		
							/**
							 * [tFTPFileList_1 main ] stop
							 */
							NB_ITERATE_tFTPGet_1++;
		
							/**
							 * [tFTPGet_1 begin ] start
							 */
		
							ok_Hash.put("tFTPGet_1", false);
							start_Hash.put("tFTPGet_1", System.currentTimeMillis());
		
							currentComponent = "tFTPGet_1";
		
							int tos_count_tFTPGet_1 = 0;
		
							int nb_file_tFTPGet_1 = 0;
							final com.enterprisedt.net.ftp.TransferCompleteStrings msg_tFTPGet_1 = new com.enterprisedt.net.ftp.TransferCompleteStrings();
		
							class FTPGetter_tFTPGet_1 {
								private com.enterprisedt.net.ftp.FTPClient ftpClient = null;
								private int count = 0;
		
								public void getAllFiles(String remoteDirectory,
										String localDirectory)
										throws java.io.IOException,
										com.enterprisedt.net.ftp.FTPException,
										java.text.ParseException {
		
									ftpClient.chdir(remoteDirectory);
									String path = ftpClient.pwd();
									String[] ftpFileNames = null;
									com.enterprisedt.net.ftp.FTPFile[] ftpFiles = null;
		
									try {
										// use dir() for Bug9562 with FTP server in
										// AS400
										ftpFileNames = ftpClient.dir(null, false);
										// Bug 13272, the same as getFiles().
									} catch (com.enterprisedt.net.ftp.FTPException e) {
		
										ftpFileNames = null;
										ftpFiles = ftpClient.dirDetails(".");
									}
		
									// if dirDetails(...) doesn't work, then use
									// dir(...), distinguish file type by FTPException
		
									if ((ftpFiles == null) && (ftpFileNames != null)) {
										// if the file is folder, catch the FTPException
										// and recur
										for (String ftpFileName : ftpFileNames) {
											try {
												downloadFile(localDirectory + "/"
														+ ftpFileName, ftpFileName);
											} catch (com.enterprisedt.net.ftp.FTPException e) {
		
												java.io.File localFile = new java.io.File(
														localDirectory + "/"
																+ ftpFileName);
		
												if (!localFile.exists()) {
													localFile.mkdir();
												}
												getAllFiles(path + "/" + ftpFileName,
														localDirectory + "/"
																+ ftpFileName);
												ftpClient.chdir(path);
											}
										}
									} else {
										for (com.enterprisedt.net.ftp.FTPFile ftpFile : ftpFiles) {
		
											if (ftpFile.isDir()) {
												if ((!(".").equals(ftpFile.getName()))
														&& (!("..").equals(ftpFile
																.getName()))) {
													java.io.File localFile = new java.io.File(
															localDirectory + "/"
																	+ ftpFile.getName());
		
													if (!localFile.exists()) {
														localFile.mkdir();
													}
													getAllFiles(
															path + "/"
																	+ ftpFile.getName(),
															localDirectory + "/"
																	+ ftpFile.getName());
													ftpClient.chdir(path);
												}
											} else if (!ftpFile.isLink()) {
												downloadFile(localDirectory + "/"
														+ ftpFile.getName(),
														ftpFile.getName());
											}
										}
									}
								}
		
								public void getFiles(String remoteDirectory,
										String localDirectory, String maskStr)
										throws java.io.IOException,
										com.enterprisedt.net.ftp.FTPException,
										java.text.ParseException {
									ftpClient.chdir(remoteDirectory);
									String[] ftpFileNames = null;
									com.enterprisedt.net.ftp.FTPFile[] ftpFiles = null;
		
									try {
										// use dir() for Bug9562 with FTP server in
										// AS400 (the same way as getAllFiles())
										ftpFileNames = ftpClient.dir(null, false);
										// Bug 13272, if dir() throw exception, use
										// dirDetails().
									} catch (com.enterprisedt.net.ftp.FTPException e) {
		
										ftpFileNames = null;
										ftpFiles = ftpClient.dirDetails(".");
									}
									// if dirDetails(...) doesn't work, then use
									// dir(...), but can not distinguish file type
		
									if ((ftpFiles == null) && (ftpFileNames != null)) {
										for (String ftpFileName : ftpFileNames) {
											if (ftpFileName.matches(maskStr)) {
												downloadFile(localDirectory + "/"
														+ ftpFileName, ftpFileName);
											}
										}
									} else {
		
										for (com.enterprisedt.net.ftp.FTPFile ftpFile : ftpFiles) {
		
											if (!ftpFile.isDir() && !ftpFile.isLink()) {
												String fileName = ftpFile.getName();
		
												if (fileName.matches(maskStr)) {
													downloadFile(localDirectory + "/"
															+ fileName, fileName);
												}
											}
										}
									}
								}
		
								public void chdir(String path)
										throws java.io.IOException,
										com.enterprisedt.net.ftp.FTPException {
									ftpClient.chdir(path);
								}
		
								public String pwd() throws java.io.IOException,
										com.enterprisedt.net.ftp.FTPException {
									return ftpClient.pwd();
								}
		
								private void downloadFile(String localFileName,
										String remoteFileName)
										throws java.io.IOException,
										com.enterprisedt.net.ftp.FTPException {
									java.io.File localFile = new java.io.File(
											localFileName);
		
									try {
		
										if (!localFile.exists()) {
											ftpClient
													.get(localFileName, remoteFileName);
		
											msg_tFTPGet_1.add("file [" + remoteFileName
													+ "] downloaded successfully.");
		
											globalMap.put("tFTPGet_1_CURRENT_STATUS",
													"File transfer OK.");
										} else {
											msg_tFTPGet_1.add("file [" + remoteFileName
													+ "] exit transmission.");
											globalMap.put("tFTPGet_1_CURRENT_STATUS",
													"No file transfered.");
										}
									} catch (com.enterprisedt.net.ftp.FTPException e) {
										msg_tFTPGet_1.add("file [" + remoteFileName
												+ "] downloaded unsuccessfully.");
										globalMap.put("tFTPGet_1_CURRENT_STATUS",
												"File transfer fail.");
										throw e;
									}
									count++;
								}
							}
							com.enterprisedt.net.ftp.FTPClient ftp_tFTPGet_1 = null;
		
							ftp_tFTPGet_1 = (com.enterprisedt.net.ftp.FTPClient) globalMap
									.get("conn_tFTPConnection_1");
		
							msg_tFTPGet_1.clearAll();
							FTPGetter_tFTPGet_1 getter_tFTPGet_1 = new FTPGetter_tFTPGet_1();
							getter_tFTPGet_1.ftpClient = ftp_tFTPGet_1;
							String remotedir_tFTPGet_1 = context.remoteDir;
							ftp_tFTPGet_1.chdir(remotedir_tFTPGet_1);
							java.util.List<String> maskList_tFTPGet_1 = new java.util.ArrayList<String>();
		
							maskList_tFTPGet_1.add(".xlsx");
							ftp_tFTPGet_1
									.setType(com.enterprisedt.net.ftp.FTPTransferType.ASCII);
							String localdir_tFTPGet_1 = context.localDir;
							// create folder if local direcotry (assigned by property)
							// not exists
							java.io.File dirHandle_tFTPGet_1 = new java.io.File(
									localdir_tFTPGet_1);
		
							if (!dirHandle_tFTPGet_1.exists()) {
								dirHandle_tFTPGet_1.mkdirs();
							}
							String root_tFTPGet_1 = getter_tFTPGet_1.pwd();
		
							for (String maskStr_tFTPGet_1 : maskList_tFTPGet_1) {
		
								/**
								 * [tFTPGet_1 begin ] stop
								 */
		
								/**
								 * [tFTPGet_1 main ] start
								 */
		
								currentComponent = "tFTPGet_1";
		
								try {
									globalMap.put("tFTPGet_1_CURRENT_STATUS",
											"No file transfered.");
									String dir_tFTPGet_1 = root_tFTPGet_1;
		
									String mask_tFTPGet_1 = maskStr_tFTPGet_1
											.replaceAll("\\\\", "/");
		
									int i_tFTPGet_1 = mask_tFTPGet_1.lastIndexOf('/');
		
									if (i_tFTPGet_1 != -1) {
										dir_tFTPGet_1 = mask_tFTPGet_1.substring(0,
												i_tFTPGet_1);
										mask_tFTPGet_1 = mask_tFTPGet_1
												.substring(i_tFTPGet_1 + 1);
									}
		
									mask_tFTPGet_1 = org.apache.oro.text.GlobCompiler
											.globToPerl5(
													mask_tFTPGet_1.toCharArray(),
													org.apache.oro.text.GlobCompiler.DEFAULT_MASK);
		
									if (dir_tFTPGet_1 != null
											&& !"".equals(dir_tFTPGet_1)) {
										if ((".*").equals(mask_tFTPGet_1)) {
											getter_tFTPGet_1.getAllFiles(dir_tFTPGet_1,
													localdir_tFTPGet_1);
										} else {
											getter_tFTPGet_1.getFiles(dir_tFTPGet_1,
													localdir_tFTPGet_1, mask_tFTPGet_1);
										}
									}
									getter_tFTPGet_1.chdir(root_tFTPGet_1);
								} catch (java.lang.Exception e) {
		
									throw (e);
		
								}
		
								tos_count_tFTPGet_1++;
		
								/**
								 * [tFTPGet_1 main ] stop
								 */
		
								/**
								 * [tFTPGet_1 end ] start
								 */
		
								currentComponent = "tFTPGet_1";
		
							}
							nb_file_tFTPGet_1 = getter_tFTPGet_1.count;
		
							msg_tFTPGet_1.add(ftp_tFTPGet_1.getDownloadCount()
									+ " files have been downloaded.");
							String[] msgAll_tFTPGet_1 = msg_tFTPGet_1.getAll();
							StringBuffer sb_tFTPGet_1 = new StringBuffer();
		
							if (msgAll_tFTPGet_1 != null) {
								for (String item_tFTPGet_1 : msgAll_tFTPGet_1) {
									sb_tFTPGet_1.append(item_tFTPGet_1).append("\n");
								}
							}
							globalMap.put("tFTPGet_1_TRANSFER_MESSAGES",
									sb_tFTPGet_1.toString());
		
							globalMap.put("tFTPGet_1_NB_FILE", nb_file_tFTPGet_1);
		
							ok_Hash.put("tFTPGet_1", true);
							end_Hash.put("tFTPGet_1", System.currentTimeMillis());
		
							/**
							 * [tFTPGet_1 end ] stop
							 */
		
							/**
							 * [tFTPFileList_1 end ] start
							 */
		
							currentComponent = "tFTPFileList_1";
		
						}
						globalMap.put("tFTPFileList_1_NB_FILE", nb_file_tFTPFileList_1);
		
						ok_Hash.put("tFTPFileList_1", true);
						end_Hash.put("tFTPFileList_1", System.currentTimeMillis());
		
						/**
						 * [tFTPFileList_1 end ] stop
						 */
					}// end the resume
		
					if (resumeEntryMethodName == null || globalResumeTicket) {
						resumeUtil
								.addLog("CHECKPOINT",
										"CONNECTION:SUBJOB_OK:tFTPFileList_1:OnSubjobOk",
										"", Thread.currentThread().getId() + "", "",
										"", "", "", "");
					}
		
					tFileList_1Process(globalMap);
		
				} catch (java.lang.Exception e) {
		
					TalendException te = new TalendException(e, currentComponent,
							globalMap);
		
					throw te;
				} catch (java.lang.Error error) {
		
					throw error;
				} finally {
		
					try {
		
						/**
						 * [tFTPFileList_1 finally ] start
						 */
		
						currentComponent = "tFTPFileList_1";
		
						/**
						 * [tFTPFileList_1 finally ] stop
						 */
		
						/**
						 * [tFTPGet_1 finally ] start
						 */
		
						currentComponent = "tFTPGet_1";
		
						/**
						 * [tFTPGet_1 finally ] stop
						 */
		
					} catch (java.lang.Exception e) {
						// ignore
					} catch (java.lang.Error error) {
						// ignore
					}
					resourceMap = null;
				}
		
				globalMap.put("tFTPFileList_1_SUBPROCESS_STATE", 1);
			}
		
			public void tFileList_1Process(final java.util.Map<String, Object> globalMap)
					throws TalendException {
				globalMap.put("tFileList_1_SUBPROCESS_STATE", 0);
		
				final boolean execStat = this.execStat;
		
				String iterateId = "";
		
				String currentComponent = "";
				java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();
		
				try {
		
					String currentMethodName = new java.lang.Exception()
							.getStackTrace()[0].getMethodName();
					boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
					if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																							// the
																							// resume
						globalResumeTicket = true;
		
						/**
						 * [tFileList_1 begin ] start
						 */
		
						int NB_ITERATE_tRunJob_1 = 0; // for statistics
		
						ok_Hash.put("tFileList_1", false);
						start_Hash.put("tFileList_1", System.currentTimeMillis());
		
						currentComponent = "tFileList_1";
		
						int tos_count_tFileList_1 = 0;
		
						String directory_tFileList_1 = context.localDir;
						final java.util.List<String> maskList_tFileList_1 = new java.util.ArrayList<String>();
						final java.util.List<java.util.regex.Pattern> patternList_tFileList_1 = new java.util.ArrayList<java.util.regex.Pattern>();
						maskList_tFileList_1.add("Pages.xlsx");
						for (final String filemask_tFileList_1 : maskList_tFileList_1) {
							String filemask_compile_tFileList_1 = filemask_tFileList_1;
		
							filemask_compile_tFileList_1 = org.apache.oro.text.GlobCompiler
									.globToPerl5(
											filemask_tFileList_1.toCharArray(),
											org.apache.oro.text.GlobCompiler.DEFAULT_MASK);
		
							java.util.regex.Pattern fileNamePattern_tFileList_1 = java.util.regex.Pattern
									.compile(filemask_compile_tFileList_1);
							patternList_tFileList_1.add(fileNamePattern_tFileList_1);
						}
						int NB_FILEtFileList_1 = 0;
		
						final boolean case_sensitive_tFileList_1 = true;
						final java.util.List<java.io.File> list_tFileList_1 = new java.util.ArrayList<java.io.File>();
						final java.util.Set<String> filePath_tFileList_1 = new java.util.HashSet<String>();
						java.io.File file_tFileList_1 = new java.io.File(
								directory_tFileList_1);
		
						file_tFileList_1.listFiles(new java.io.FilenameFilter() {
							public boolean accept(java.io.File dir, String name) {
								java.io.File file = new java.io.File(dir, name);
								if (!file.isDirectory()) {
		
									String fileName_tFileList_1 = file.getName();
									for (final java.util.regex.Pattern fileNamePattern_tFileList_1 : patternList_tFileList_1) {
										if (fileNamePattern_tFileList_1.matcher(
												fileName_tFileList_1).matches()) {
											if (!filePath_tFileList_1.contains(file
													.getAbsolutePath())) {
												list_tFileList_1.add(file);
												filePath_tFileList_1.add(file
														.getAbsolutePath());
											}
										}
									}
								}
								return true;
							}
						});
						java.util.Collections.sort(list_tFileList_1);
		
						for (int i_tFileList_1 = 0; i_tFileList_1 < list_tFileList_1
								.size(); i_tFileList_1++) {
							java.io.File files_tFileList_1 = list_tFileList_1
									.get(i_tFileList_1);
							String fileName_tFileList_1 = files_tFileList_1.getName();
		
							String currentFileName_tFileList_1 = files_tFileList_1
									.getName();
							String currentFilePath_tFileList_1 = files_tFileList_1
									.getAbsolutePath();
							String currentFileDirectory_tFileList_1 = files_tFileList_1
									.getParent();
							String currentFileExtension_tFileList_1 = null;
		
							if (files_tFileList_1.getName().contains(".")
									&& files_tFileList_1.isFile()) {
								currentFileExtension_tFileList_1 = files_tFileList_1
										.getName().substring(
												files_tFileList_1.getName()
														.lastIndexOf(".") + 1);
							} else {
								currentFileExtension_tFileList_1 = "";
							}
		
							NB_FILEtFileList_1++;
							globalMap.put("tFileList_1_CURRENT_FILE",
									currentFileName_tFileList_1);
							globalMap.put("tFileList_1_CURRENT_FILEPATH",
									currentFilePath_tFileList_1);
							globalMap.put("tFileList_1_CURRENT_FILEDIRECTORY",
									currentFileDirectory_tFileList_1);
							globalMap.put("tFileList_1_CURRENT_FILEEXTENSION",
									currentFileExtension_tFileList_1);
							globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);
		
							/**
							 * [tFileList_1 begin ] stop
							 */
		
							/**
							 * [tFileList_1 main ] start
							 */
		
							currentComponent = "tFileList_1";
		
							tos_count_tFileList_1++;
		
							/**
							 * [tFileList_1 main ] stop
							 */
							NB_ITERATE_tRunJob_1++;
		
							/**
							 * [tRunJob_1 begin ] start
							 */
		
							ok_Hash.put("tRunJob_1", false);
							start_Hash.put("tRunJob_1", System.currentTimeMillis());
		
							currentComponent = "tRunJob_1";
		
							int tos_count_tRunJob_1 = 0;
		
							class DealChildJobLibrary_tRunJob_1 {
		
								public String replaceJarPathsFromCrcMap(
										String originalClassPathLine)
										throws java.lang.Exception {
									String classPathLine = "";
									String crcMapPath = new java.io.File("../crcMap")
											.getCanonicalPath();
									if (isNeedAddLibsPath(crcMapPath)) {
										java.util.Map<String, String> crcMap = null;
										java.io.ObjectInputStream ois = new ObjectInputStream(
												new java.io.FileInputStream(crcMapPath));
										crcMap = (java.util.Map<String, String>) ois
												.readObject();
										ois.close();
										classPathLine = addLibsPath(
												originalClassPathLine, crcMap);
									} else {
										classPathLine = originalClassPathLine;
									}
									return classPathLine;
								}
		
								private boolean isNeedAddLibsPath(String crcMapPath) {
									if (!(new java.io.File(crcMapPath).exists())) {// when
																					// not
																					// use
																					// cache
										return false;
									}
									return true;
								}
		
								private String addLibsPath(String line,
										java.util.Map<String, String> crcMap) {
									for (java.util.Map.Entry<String, String> entry : crcMap
											.entrySet()) {
										line = adaptLibPaths(line, entry);
									}
									return line;
								}
		
								private String adaptLibPaths(String line,
										java.util.Map.Entry<String, String> entry) {
									String jarName = entry.getValue();
									String crc = entry.getKey();
									String libStringFinder = "../lib/" + jarName;
									if (line.contains(libStringFinder)) {
										line = line.replace(libStringFinder,
												"../../../cache/lib/" + crc + "/"
														+ jarName);
									} else if (line.contains(":$ROOT_PATH/" + jarName
											+ ":")) {
										line = line.replace(":$ROOT_PATH/" + jarName
												+ ":",
												":$ROOT_PATH/../../../cache/lib/" + crc
														+ "/" + jarName + ":");
									} else if (line.contains(";" + jarName + ";")) {
										line = line.replace(";" + jarName + ";",
												";../../../cache/lib/" + crc + "/"
														+ jarName + ";");
									}
									return line;
								}
		
							}
							DealChildJobLibrary_tRunJob_1 dealChildJobLibrary_tRunJob_1 = new DealChildJobLibrary_tRunJob_1();
		
							/**
							 * [tRunJob_1 begin ] stop
							 */
		
							/**
							 * [tRunJob_1 main ] start
							 */
		
							currentComponent = "tRunJob_1";
		
							java.util.List<String> paraList_tRunJob_1 = new java.util.ArrayList<String>();
		
							String osName_tRunJob_1 = System.getProperty("os.name");
							if (osName_tRunJob_1 != null
									&& osName_tRunJob_1.toLowerCase().startsWith("win")) {
		
								paraList_tRunJob_1
										.add("C:/Program Files/Java/jre1.8.0_51/bin/java.exe");
		
								paraList_tRunJob_1.add("-Xms256M");
		
								paraList_tRunJob_1.add("-Xmx1024M");
		
								paraList_tRunJob_1.add("-Dfile.encoding=UTF-8");
		
								paraList_tRunJob_1.add("-cp");
		
								paraList_tRunJob_1
										.add(dealChildJobLibrary_tRunJob_1
												.replaceJarPathsFromCrcMap("E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/target/classes;.;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-beanutils-1.8.3.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-collections-3.2.1.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-lang-2.6.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-logging-1.1.1.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/dom4j-1.6.1.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/ezmorph-1.0.6.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/geronimo-stax-api_1.0_spec-1.0.1.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/jakarta-oro-2.0.8.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/jaxen-1.1.1.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/json-lib-2.4-jdk15.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/log4j-1.2.15.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/log4j-1.2.16.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/mongo-java-driver-2.13.2.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-3.11-20141221_modified_talend.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-ooxml-3.11-20141221_modified_talend.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-ooxml-schemas-3.11-20141221.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-scratchpad-3.11-20141221.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/xmlbeans-2.6.0.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/xom-1.2.7.jar;E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib;"));
		
								paraList_tRunJob_1.add("beproject.trans1_0_1.Trans1");
		
								paraList_tRunJob_1.add("--father_pid=" + pid);
		
								paraList_tRunJob_1.add("--root_pid=" + rootPid);
		
								paraList_tRunJob_1.add("--father_node=tRunJob_1");
		
								paraList_tRunJob_1.add("--context=Default");
		
								paraList_tRunJob_1.add("%*");
		
							} else {
		
								paraList_tRunJob_1
										.add("C:/Program Files/Java/jre1.8.0_51/bin/java.exe");
		
								paraList_tRunJob_1.add("-Xms256M");
		
								paraList_tRunJob_1.add("-Xmx1024M");
		
								paraList_tRunJob_1.add("-Dfile.encoding=UTF-8");
		
								paraList_tRunJob_1.add("-cp");
		
								paraList_tRunJob_1
										.add(dealChildJobLibrary_tRunJob_1
												.replaceJarPathsFromCrcMap(
														"E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/target/classes:.:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-beanutils-1.8.3.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-collections-3.2.1.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-lang-2.6.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/commons-logging-1.1.1.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/dom4j-1.6.1.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/ezmorph-1.0.6.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/geronimo-stax-api_1.0_spec-1.0.1.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/jakarta-oro-2.0.8.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/jaxen-1.1.1.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/json-lib-2.4-jdk15.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/log4j-1.2.15.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/log4j-1.2.16.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/mongo-java-driver-2.13.2.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-3.11-20141221_modified_talend.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-ooxml-3.11-20141221_modified_talend.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-ooxml-schemas-3.11-20141221.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/poi-scratchpad-3.11-20141221.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/xmlbeans-2.6.0.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib/xom-1.2.7.jar:E:/TOS_BD-20150908_1633-V6.0.1/TOS_BD-20150908_1633-V6.0.1/workspace/.Java/lib:")
												.replace("$ROOT_PATH",
														System.getProperty("user.dir")));
		
								paraList_tRunJob_1.add("beproject.trans1_0_1.Trans1");
		
								paraList_tRunJob_1.add("--father_pid=" + pid);
		
								paraList_tRunJob_1.add("--root_pid=" + rootPid);
		
								paraList_tRunJob_1.add("--father_node=tRunJob_1");
		
								paraList_tRunJob_1.add("--context=Default");
		
								paraList_tRunJob_1.add("$@");
		
							}
		
							// for feature:10589
		
							paraList_tRunJob_1.add("--stat_port=" + null);
		
							if (resuming_logs_dir_path != null) {
								paraList_tRunJob_1.add("--resuming_logs_dir_path="
										+ resuming_logs_dir_path);
							}
							String childResumePath_tRunJob_1 = ResumeUtil
									.getChildJobCheckPointPath(resuming_checkpoint_path);
							String tRunJobName_tRunJob_1 = ResumeUtil
									.getRighttRunJob(resuming_checkpoint_path);
							if ("tRunJob_1".equals(tRunJobName_tRunJob_1)
									&& childResumePath_tRunJob_1 != null) {
								paraList_tRunJob_1
										.add("--resuming_checkpoint_path="
												+ ResumeUtil
														.getChildJobCheckPointPath(resuming_checkpoint_path));
							}
							paraList_tRunJob_1.add("--parent_part_launcher=JOB:"
									+ jobName + "/NODE:tRunJob_1");
		
							java.util.Map<String, Object> parentContextMap_tRunJob_1 = new java.util.HashMap<String, Object>();
		
							context.synchronizeContext();
							java.util.Enumeration<?> propertyNames_tRunJob_1 = context
									.propertyNames();
							while (propertyNames_tRunJob_1.hasMoreElements()) {
								String key_tRunJob_1 = (String) propertyNames_tRunJob_1
										.nextElement();
								Object value_tRunJob_1 = (Object) context
										.get(key_tRunJob_1);
								paraList_tRunJob_1.add("--context_param "
										+ key_tRunJob_1 + "=" + value_tRunJob_1);
							}
		
							parentContextMap_tRunJob_1.put("host", context.host);
		
							parentContextMap_tRunJob_1.put("port", context.port);
		
							parentContextMap_tRunJob_1
									.put("username", context.username);
		
							parentContextMap_tRunJob_1
									.put("password", context.password);
		
							parentContextMap_tRunJob_1.put("remoteDir",
									context.remoteDir);
		
							parentContextMap_tRunJob_1
									.put("localDir", context.localDir);
		
							Object obj_tRunJob_1 = null;
		
							System.out
									.println("tRunJob_1 in Mainjob call Trans1 with:\n\n"
											+ paraList_tRunJob_1 + "\n");
		
							Runtime runtime_tRunJob_1 = Runtime.getRuntime();
							final Process ps_tRunJob_1;
							ps_tRunJob_1 = runtime_tRunJob_1
									.exec((String[]) paraList_tRunJob_1
											.toArray(new String[paraList_tRunJob_1
													.size()]));
		
							Thread normal_tRunJob_1 = new Thread() {
								public void run() {
									try {
										java.io.BufferedReader reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														ps_tRunJob_1.getInputStream()));
										String line = "";
										try {
											while ((line = reader.readLine()) != null) {
												System.out.println(line);
											}
										} finally {
											reader.close();
										}
									} catch (java.io.IOException ioe) {
		
										ioe.printStackTrace();
									}
								}
							};
		
							normal_tRunJob_1.start();
		
							final StringBuffer errorMsg_tRunJob_1 = new StringBuffer();
							Thread error_tRunJob_1 = new Thread() {
								public void run() {
									try {
										java.io.BufferedReader reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														ps_tRunJob_1.getErrorStream()));
										String line = "";
										try {
											while ((line = reader.readLine()) != null) {
												errorMsg_tRunJob_1.append(line).append(
														"\n");
											}
										} finally {
											reader.close();
										}
									} catch (java.io.IOException ioe) {
		
										ioe.printStackTrace();
									}
								}
							};
							error_tRunJob_1.start();
		
							// 0 indicates normal termination
							int result_tRunJob_1 = ps_tRunJob_1.waitFor();
							normal_tRunJob_1.join(10000);
							error_tRunJob_1.join(10000);
		
							globalMap.put("tRunJob_1_CHILD_RETURN_CODE",
									result_tRunJob_1);
							if (result_tRunJob_1 != 0) {
								globalMap.put("tRunJob_1_CHILD_EXCEPTION_STACKTRACE",
										errorMsg_tRunJob_1.toString());
		
								System.err.println("Child job returns "
										+ result_tRunJob_1
										+ ". It doesn't terminate normally.\n"
										+ errorMsg_tRunJob_1.toString());
		
							}
		
							tos_count_tRunJob_1++;
		
							/**
							 * [tRunJob_1 main ] stop
							 */
		
							/**
							 * [tRunJob_1 end ] start
							 */
		
							currentComponent = "tRunJob_1";
		
							ok_Hash.put("tRunJob_1", true);
							end_Hash.put("tRunJob_1", System.currentTimeMillis());
		
							/**
							 * [tRunJob_1 end ] stop
							 */
		
							/**
							 * [tFileList_1 end ] start
							 */
		
							currentComponent = "tFileList_1";
		
						}
						globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);
		
						ok_Hash.put("tFileList_1", true);
						end_Hash.put("tFileList_1", System.currentTimeMillis());
		
						/**
						 * [tFileList_1 end ] stop
						 */
					}// end the resume
		
				} catch (java.lang.Exception e) {
		
					TalendException te = new TalendException(e, currentComponent,
							globalMap);
		
					throw te;
				} catch (java.lang.Error error) {
		
					throw error;
				} finally {
		
					try {
		
						/**
						 * [tFileList_1 finally ] start
						 */
		
						currentComponent = "tFileList_1";
		
						/**
						 * [tFileList_1 finally ] stop
						 */
		
						/**
						 * [tRunJob_1 finally ] start
						 */
		
						currentComponent = "tRunJob_1";
		
						/**
						 * [tRunJob_1 finally ] stop
						 */
		
					} catch (java.lang.Exception e) {
						// ignore
					} catch (java.lang.Error error) {
						// ignore
					}
					resourceMap = null;
				}
		
				globalMap.put("tFileList_1_SUBPROCESS_STATE", 1);
			}
		
			public static class row1Struct implements
					routines.system.IPersistableRow<row1Struct> {
				final static byte[] commonByteArrayLock_BEPROJECT_Mainjob = new byte[0];
				static byte[] commonByteArray_BEPROJECT_Mainjob = new byte[0];
		
				public java.util.Date moment;
		
				public java.util.Date getMoment() {
					return this.moment;
				}
		
				public String pid;
		
				public String getPid() {
					return this.pid;
				}
		
				public String root_pid;
		
				public String getRoot_pid() {
					return this.root_pid;
				}
		
				public String father_pid;
		
				public String getFather_pid() {
					return this.father_pid;
				}
		
				public String project;
		
				public String getProject() {
					return this.project;
				}
		
				public String job;
		
				public String getJob() {
					return this.job;
				}
		
				public String context;
		
				public String getContext() {
					return this.context;
				}
		
				public Integer priority;
		
				public Integer getPriority() {
					return this.priority;
				}
		
				public String type;
		
				public String getType() {
					return this.type;
				}
		
				public String origin;
		
				public String getOrigin() {
					return this.origin;
				}
		
				public String message;
		
				public String getMessage() {
					return this.message;
				}
		
				public Integer code;
		
				public Integer getCode() {
					return this.code;
				}
		
				private java.util.Date readDate(ObjectInputStream dis)
						throws IOException {
					java.util.Date dateReturn = null;
					int length = 0;
					length = dis.readByte();
					if (length == -1) {
						dateReturn = null;
					} else {
						dateReturn = new Date(dis.readLong());
					}
					return dateReturn;
				}
		
				private void writeDate(java.util.Date date1, ObjectOutputStream dos)
						throws IOException {
					if (date1 == null) {
						dos.writeByte(-1);
					} else {
						dos.writeByte(0);
						dos.writeLong(date1.getTime());
					}
				}
		
				private String readString(ObjectInputStream dis) throws IOException {
					String strReturn = null;
					int length = 0;
					length = dis.readInt();
					if (length == -1) {
						strReturn = null;
					} else {
						if (length > commonByteArray_BEPROJECT_Mainjob.length) {
							if (length < 1024
									&& commonByteArray_BEPROJECT_Mainjob.length == 0) {
								commonByteArray_BEPROJECT_Mainjob = new byte[1024];
							} else {
								commonByteArray_BEPROJECT_Mainjob = new byte[2 * length];
							}
						}
						dis.readFully(commonByteArray_BEPROJECT_Mainjob, 0, length);
						strReturn = new String(commonByteArray_BEPROJECT_Mainjob, 0,
								length, utf8Charset);
					}
					return strReturn;
				}
		
				private void writeString(String str, ObjectOutputStream dos)
						throws IOException {
					if (str == null) {
						dos.writeInt(-1);
					} else {
						byte[] byteArray = str.getBytes(utf8Charset);
						dos.writeInt(byteArray.length);
						dos.write(byteArray);
					}
				}
		
				private Integer readInteger(ObjectInputStream dis) throws IOException {
					Integer intReturn;
					int length = 0;
					length = dis.readByte();
					if (length == -1) {
						intReturn = null;
					} else {
						intReturn = dis.readInt();
					}
					return intReturn;
				}
		
				private void writeInteger(Integer intNum, ObjectOutputStream dos)
						throws IOException {
					if (intNum == null) {
						dos.writeByte(-1);
					} else {
						dos.writeByte(0);
						dos.writeInt(intNum);
					}
				}
		
				public void readData(ObjectInputStream dis) {
		
					synchronized (commonByteArrayLock_BEPROJECT_Mainjob) {
		
						try {
		
							int length = 0;
		
							this.moment = readDate(dis);
		
							this.pid = readString(dis);
		
							this.root_pid = readString(dis);
		
							this.father_pid = readString(dis);
		
							this.project = readString(dis);
		
							this.job = readString(dis);
		
							this.context = readString(dis);
		
							this.priority = readInteger(dis);
		
							this.type = readString(dis);
		
							this.origin = readString(dis);
		
							this.message = readString(dis);
		
							this.code = readInteger(dis);
		
						} catch (IOException e) {
							throw new RuntimeException(e);
		
						}
		
					}
		
				}
		
				public void writeData(ObjectOutputStream dos) {
					try {
		
						// java.util.Date
		
						writeDate(this.moment, dos);
		
						// String
		
						writeString(this.pid, dos);
		
						// String
		
						writeString(this.root_pid, dos);
		
						// String
		
						writeString(this.father_pid, dos);
		
						// String
		
						writeString(this.project, dos);
		
						// String
		
						writeString(this.job, dos);
		
						// String
		
						writeString(this.context, dos);
		
						// Integer
		
						writeInteger(this.priority, dos);
		
						// String
		
						writeString(this.type, dos);
		
						// String
		
						writeString(this.origin, dos);
		
						// String
		
						writeString(this.message, dos);
		
						// Integer
		
						writeInteger(this.code, dos);
		
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
		
				}
		
				public String toString() {
		
					StringBuilder sb = new StringBuilder();
					sb.append(super.toString());
					sb.append("[");
					sb.append("moment=" + String.valueOf(moment));
					sb.append(",pid=" + pid);
					sb.append(",root_pid=" + root_pid);
					sb.append(",father_pid=" + father_pid);
					sb.append(",project=" + project);
					sb.append(",job=" + job);
					sb.append(",context=" + context);
					sb.append(",priority=" + String.valueOf(priority));
					sb.append(",type=" + type);
					sb.append(",origin=" + origin);
					sb.append(",message=" + message);
					sb.append(",code=" + String.valueOf(code));
					sb.append("]");
		
					return sb.toString();
				}
		
				/**
				 * Compare keys
				 */
				public int compareTo(row1Struct other) {
		
					int returnValue = -1;
		
					return returnValue;
				}
		
				private int checkNullsAndCompare(Object object1, Object object2) {
					int returnValue = 0;
					if (object1 instanceof Comparable && object2 instanceof Comparable) {
						returnValue = ((Comparable) object1).compareTo(object2);
					} else if (object1 != null && object2 != null) {
						returnValue = compareStrings(object1.toString(),
								object2.toString());
					} else if (object1 == null && object2 != null) {
						returnValue = 1;
					} else if (object1 != null && object2 == null) {
						returnValue = -1;
					} else {
						returnValue = 0;
					}
		
					return returnValue;
				}
		
				private int compareStrings(String string1, String string2) {
					return string1.compareTo(string2);
				}
		
			}
		
			public void tLogCatcher_1Process(
					final java.util.Map<String, Object> globalMap)
					throws TalendException {
				globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 0);
		
				final boolean execStat = this.execStat;
		
				String iterateId = "";
		
				String currentComponent = "";
				java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();
		
				try {
		
					String currentMethodName = new java.lang.Exception()
							.getStackTrace()[0].getMethodName();
					boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
					if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																							// the
																							// resume
						globalResumeTicket = true;
		
						row1Struct row1 = new row1Struct();
		
						/**
						 * [tBufferOutput_1 begin ] start
						 */
		
						ok_Hash.put("tBufferOutput_1", false);
						start_Hash.put("tBufferOutput_1", System.currentTimeMillis());
		
						currentComponent = "tBufferOutput_1";
		
						int tos_count_tBufferOutput_1 = 0;
		
						/**
						 * [tBufferOutput_1 begin ] stop
						 */
		
						/**
						 * [tLogCatcher_1 begin ] start
						 */
		
						ok_Hash.put("tLogCatcher_1", false);
						start_Hash.put("tLogCatcher_1", System.currentTimeMillis());
		
						currentComponent = "tLogCatcher_1";
		
						int tos_count_tLogCatcher_1 = 0;
		
						for (LogCatcherUtils.LogCatcherMessage lcm : tLogCatcher_1
								.getMessages()) {
							row1.type = lcm.getType();
							row1.origin = (lcm.getOrigin() == null
									|| lcm.getOrigin().length() < 1 ? null : lcm
									.getOrigin());
							row1.priority = lcm.getPriority();
							row1.message = lcm.getMessage();
							row1.code = lcm.getCode();
		
							row1.moment = java.util.Calendar.getInstance().getTime();
		
							row1.pid = pid;
							row1.root_pid = rootPid;
							row1.father_pid = fatherPid;
		
							row1.project = projectName;
							row1.job = jobName;
							row1.context = contextStr;
		
							/**
							 * [tLogCatcher_1 begin ] stop
							 */
		
							/**
							 * [tLogCatcher_1 main ] start
							 */
		
							currentComponent = "tLogCatcher_1";
		
							tos_count_tLogCatcher_1++;
		
							/**
							 * [tLogCatcher_1 main ] stop
							 */
		
							/**
							 * [tBufferOutput_1 main ] start
							 */
		
							currentComponent = "tBufferOutput_1";
		
							String[] row_tBufferOutput_1 = new String[] { "", "", "",
									"", "", "", "", "", "", "", "", "", };
							if (row1.moment != null) {
		
								row_tBufferOutput_1[0] = FormatterUtils.format_Date(
										row1.moment, "yyyy-MM-dd HH:mm:ss");
		
							} else {
								row_tBufferOutput_1[0] = null;
							}
							if (row1.pid != null) {
		
								row_tBufferOutput_1[1] = row1.pid;
		
							} else {
								row_tBufferOutput_1[1] = null;
							}
							if (row1.root_pid != null) {
		
								row_tBufferOutput_1[2] = row1.root_pid;
		
							} else {
								row_tBufferOutput_1[2] = null;
							}
							if (row1.father_pid != null) {
		
								row_tBufferOutput_1[3] = row1.father_pid;
		
							} else {
								row_tBufferOutput_1[3] = null;
							}
							if (row1.project != null) {
		
								row_tBufferOutput_1[4] = row1.project;
		
							} else {
								row_tBufferOutput_1[4] = null;
							}
							if (row1.job != null) {
		
								row_tBufferOutput_1[5] = row1.job;
		
							} else {
								row_tBufferOutput_1[5] = null;
							}
							if (row1.context != null) {
		
								row_tBufferOutput_1[6] = row1.context;
		
							} else {
								row_tBufferOutput_1[6] = null;
							}
							if (row1.priority != null) {
		
								row_tBufferOutput_1[7] = String.valueOf(row1.priority);
		
							} else {
								row_tBufferOutput_1[7] = null;
							}
							if (row1.type != null) {
		
								row_tBufferOutput_1[8] = row1.type;
		
							} else {
								row_tBufferOutput_1[8] = null;
							}
							if (row1.origin != null) {
		
								row_tBufferOutput_1[9] = row1.origin;
		
							} else {
								row_tBufferOutput_1[9] = null;
							}
							if (row1.message != null) {
		
								row_tBufferOutput_1[10] = row1.message;
		
							} else {
								row_tBufferOutput_1[10] = null;
							}
							if (row1.code != null) {
		
								row_tBufferOutput_1[11] = String.valueOf(row1.code);
		
							} else {
								row_tBufferOutput_1[11] = null;
							}
							globalBuffer.add(row_tBufferOutput_1);
		
							tos_count_tBufferOutput_1++;
		
							/**
							 * [tBufferOutput_1 main ] stop
							 */
		
							/**
							 * [tLogCatcher_1 end ] start
							 */
		
							currentComponent = "tLogCatcher_1";
		
						}
		
						ok_Hash.put("tLogCatcher_1", true);
						end_Hash.put("tLogCatcher_1", System.currentTimeMillis());
		
						/**
						 * [tLogCatcher_1 end ] stop
						 */
		
						/**
						 * [tBufferOutput_1 end ] start
						 */
		
						currentComponent = "tBufferOutput_1";
		
						ok_Hash.put("tBufferOutput_1", true);
						end_Hash.put("tBufferOutput_1", System.currentTimeMillis());
		
						/**
						 * [tBufferOutput_1 end ] stop
						 */
		
					}// end the resume
		
				} catch (java.lang.Exception e) {
		
					TalendException te = new TalendException(e, currentComponent,
							globalMap);
		
					throw te;
				} catch (java.lang.Error error) {
		
					throw error;
				} finally {
		
					try {
		
						/**
						 * [tLogCatcher_1 finally ] start
						 */
		
						currentComponent = "tLogCatcher_1";
		
						/**
						 * [tLogCatcher_1 finally ] stop
						 */
		
						/**
						 * [tBufferOutput_1 finally ] start
						 */
		
						currentComponent = "tBufferOutput_1";
		
						/**
						 * [tBufferOutput_1 finally ] stop
						 */
		
					} catch (java.lang.Exception e) {
						// ignore
					} catch (java.lang.Error error) {
						// ignore
					}
					resourceMap = null;
				}
		
				globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 1);
			}
		
			public String resuming_logs_dir_path = null;
			public String resuming_checkpoint_path = null;
			public String parent_part_launcher = null;
			private String resumeEntryMethodName = null;
			private boolean globalResumeTicket = false;
		
			public boolean watch = false;
			// portStats is null, it means don't execute the statistics
			public Integer portStats = null;
			public int portTraces = 4334;
			public String clientHost;
			public String defaultClientHost = "localhost";
			public String contextStr = "Default";
			public boolean isDefaultContext = true;
			public String pid = "0";
			public String rootPid = null;
			public String fatherPid = null;
			public String fatherNode = null;
			public long startTime = 0;
			public boolean isChildJob = false;
			public String log4jLevel = "";
		
			private boolean execStat = true;
		
			private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
				protected java.util.Map<String, String> initialValue() {
					java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
					threadRunResultMap.put("errorCode", null);
					threadRunResultMap.put("status", "");
					return threadRunResultMap;
				};
			};
		
			private java.util.Properties context_param = new java.util.Properties();
			public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();
		
			public String status = "";
		
			public static void main(String[] args) {
				final Mainjob MainjobClass = new Mainjob();
		
				int exitCode = MainjobClass.runJobInTOS(args);
		
				System.exit(exitCode);
			}
		
			public String[][] runJob(String[] args) {
		
				int exitCode = runJobInTOS(args);
				String[][] bufferValue = (String[][]) globalBuffer
						.toArray(new String[globalBuffer.size()][]);
		
				return bufferValue;
			}
		
			public boolean hastBufferOutputComponent() {
				boolean hastBufferOutput = false;
		
				hastBufferOutput = true;
		
				return hastBufferOutput;
			}
		
			public int runJobInTOS(String[] args) {
				// reset status
				status = "";
		
				String lastStr = "";
				for (String arg : args) {
					if (arg.equalsIgnoreCase("--context_param")) {
						lastStr = arg;
					} else if (lastStr.equals("")) {
						evalParam(arg);
					} else {
						evalParam(lastStr + " " + arg);
						lastStr = "";
					}
				}
		
				if (clientHost == null) {
					clientHost = defaultClientHost;
				}
		
				if (pid == null || "0".equals(pid)) {
					pid = TalendString.getAsciiRandomString(6);
				}
		
				if (rootPid == null) {
					rootPid = pid;
				}
				if (fatherPid == null) {
					fatherPid = pid;
				} else {
					isChildJob = true;
				}
		
				try {
					// call job/subjob with an existing context, like:
					// --context=production. if without this parameter, there will use
					// the default context instead.
					java.io.InputStream inContext = Mainjob.class.getClassLoader()
							.getResourceAsStream(
									"beproject/mainjob_0_1/contexts/" + contextStr
											+ ".properties");
					if (isDefaultContext && inContext == null) {
		
					} else {
						if (inContext != null) {
							// defaultProps is in order to keep the original context
							// value
							defaultProps.load(inContext);
							inContext.close();
							context = new ContextProperties(defaultProps);
						} else {
							// print info and job continue to run, for case:
							// context_param is not empty.
							System.err.println("Could not find the context "
									+ contextStr);
						}
					}
		
					if (!context_param.isEmpty()) {
						context.putAll(context_param);
					}
					context.host = (String) context.getProperty("host");
					try {
						context.port = routines.system.ParserUtils
								.parseTo_Integer(context.getProperty("port"));
					} catch (NumberFormatException e) {
						context.port = null;
					}
					context.username = (String) context.getProperty("username");
					context.password = (String) context.getProperty("password");
					context.remoteDir = (String) context.getProperty("remoteDir");
					context.localDir = (String) context.getProperty("localDir");
				} catch (java.io.IOException ie) {
					System.err.println("Could not load context " + contextStr);
					ie.printStackTrace();
				}
		
				// get context value from parent directly
				if (parentContextMap != null && !parentContextMap.isEmpty()) {
					if (parentContextMap.containsKey("host")) {
						context.host = (String) parentContextMap.get("host");
					}
					if (parentContextMap.containsKey("port")) {
						context.port = (Integer) parentContextMap.get("port");
					}
					if (parentContextMap.containsKey("username")) {
						context.username = (String) parentContextMap.get("username");
					}
					if (parentContextMap.containsKey("password")) {
						context.password = (String) parentContextMap.get("password");
					}
					if (parentContextMap.containsKey("remoteDir")) {
						context.remoteDir = (String) parentContextMap.get("remoteDir");
					}
					if (parentContextMap.containsKey("localDir")) {
						context.localDir = (String) parentContextMap.get("localDir");
					}
				}
		
				// Resume: init the resumeUtil
				resumeEntryMethodName = ResumeUtil
						.getResumeEntryMethodName(resuming_checkpoint_path);
				resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
				resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
						jobName, contextStr, jobVersion);
		
				List<String> parametersToEncrypt = new java.util.ArrayList<String>();
				// Resume: jobStart
				resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
						parent_part_launcher, Thread.currentThread().getId() + "", "",
						"", "", "",
						resumeUtil.convertToJsonText(context, parametersToEncrypt));
		
				java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
				globalMap.put("concurrentHashMap", concurrentHashMap);
		
				long startUsedMemory = Runtime.getRuntime().totalMemory()
						- Runtime.getRuntime().freeMemory();
				long endUsedMemory = 0;
				long end = 0;
		
				startTime = System.currentTimeMillis();
		
				this.globalResumeTicket = true;// to run tPreJob
		
				this.globalResumeTicket = false;// to run others jobs
		
				try {
					errorCode = null;
					tFTPConnection_1Process(globalMap);
					if (!"failure".equals(status)) {
						status = "end";
					}
				} catch (TalendException e_tFTPConnection_1) {
					globalMap.put("tFTPConnection_1_SUBPROCESS_STATE", -1);
		
					e_tFTPConnection_1.printStackTrace();
		
				}
		
				this.globalResumeTicket = true;// to run tPostJob
		
				end = System.currentTimeMillis();
		
				if (watch) {
					System.out.println((end - startTime) + " milliseconds");
				}
		
				endUsedMemory = Runtime.getRuntime().totalMemory()
						- Runtime.getRuntime().freeMemory();
				if (false) {
					System.out.println((endUsedMemory - startUsedMemory)
							+ " bytes memory increase when running : Mainjob");
				}
		
				int returnCode = 0;
				if (errorCode == null) {
					returnCode = status != null && status.equals("failure") ? 1 : 0;
				} else {
					returnCode = errorCode.intValue();
				}
				resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
						Thread.currentThread().getId() + "", "", "" + returnCode, "",
						"", "");
		
				return returnCode;
		
			}
		
			// only for OSGi env
			public void destroy() {
				closeFtpConnections();
		
			}
		
			private void closeFtpConnections() {
				try {
					Object obj_conn;
					obj_conn = globalMap.remove("conn_tFTPConnection_1");
					if (obj_conn != null) {
						((com.enterprisedt.net.ftp.FTPClient) obj_conn).quit();
					}
				} catch (java.lang.Exception e) {
				}
			}
		
			private java.util.Map<String, Object> getSharedConnections4REST() {
				java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		
				connections.put("conn_tFTPConnection_1",
						globalMap.get("conn_tFTPConnection_1"));
		
				return connections;
			}
		
			private void evalParam(String arg) {
				if (arg.startsWith("--resuming_logs_dir_path")) {
					resuming_logs_dir_path = arg.substring(25);
				} else if (arg.startsWith("--resuming_checkpoint_path")) {
					resuming_checkpoint_path = arg.substring(27);
				} else if (arg.startsWith("--parent_part_launcher")) {
					parent_part_launcher = arg.substring(23);
				} else if (arg.startsWith("--watch")) {
					watch = true;
				} else if (arg.startsWith("--stat_port=")) {
					String portStatsStr = arg.substring(12);
					if (portStatsStr != null && !portStatsStr.equals("null")) {
						portStats = Integer.parseInt(portStatsStr);
					}
				} else if (arg.startsWith("--trace_port=")) {
					portTraces = Integer.parseInt(arg.substring(13));
				} else if (arg.startsWith("--client_host=")) {
					clientHost = arg.substring(14);
				} else if (arg.startsWith("--context=")) {
					contextStr = arg.substring(10);
					isDefaultContext = false;
				} else if (arg.startsWith("--father_pid=")) {
					fatherPid = arg.substring(13);
				} else if (arg.startsWith("--root_pid=")) {
					rootPid = arg.substring(11);
				} else if (arg.startsWith("--father_node=")) {
					fatherNode = arg.substring(14);
				} else if (arg.startsWith("--pid=")) {
					pid = arg.substring(6);
				} else if (arg.startsWith("--context_param")) {
					String keyValue = arg.substring(16);
					int index = -1;
					if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
						if (fatherPid == null) {
							context_param.put(keyValue.substring(0, index),
									replaceEscapeChars(keyValue.substring(index + 1)));
						} else { // the subjob won't escape the especial chars
							context_param.put(keyValue.substring(0, index),
									keyValue.substring(index + 1));
						}
					}
				} else if (arg.startsWith("--log4jLevel=")) {
					log4jLevel = arg.substring(13);
				}
		
			}
		
			private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" },
					{ "\\'", "\'" }, { "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" },
					{ "\\t", "\t" } };
		
			private String replaceEscapeChars(String keyValue) {
		
				if (keyValue == null || ("").equals(keyValue.trim())) {
					return keyValue;
				}
		
				StringBuilder result = new StringBuilder();
				int currIndex = 0;
				while (currIndex < keyValue.length()) {
					int index = -1;
					// judege if the left string includes escape chars
					for (String[] strArray : escapeChars) {
						index = keyValue.indexOf(strArray[0], currIndex);
						if (index >= 0) {
		
							result.append(keyValue.substring(currIndex,
									index + strArray[0].length()).replace(strArray[0],
									strArray[1]));
							currIndex = index + strArray[0].length();
							break;
						}
					}
					// if the left string doesn't include escape chars, append the left
					// into the result
					if (index < 0) {
						result.append(keyValue.substring(currIndex));
						currIndex = currIndex + keyValue.length();
					}
				}
		
				return result.toString();
			}
		
			public Integer getErrorCode() {
				return errorCode;
			}
		
			public String getStatus() {
				return status;
			}
		
			ResumeUtil resumeUtil = null;
		}
		/************************************************************************************************
		 * 71864 characters generated by Talend Open Studio for Big Data on the 27
		 * April, 2016 12:01:08 AM IST
		 ************************************************************************************************/
		
	}
}
