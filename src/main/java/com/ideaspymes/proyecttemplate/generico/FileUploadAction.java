package com.ideaspymes.proyecttemplate.generico;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class FileUploadAction extends HttpServlet {

    private static final long serialVersionUID = 3447685998419256747L;
    private static final String RESP_SUCCESS = "{\"jsonrpc\" : \"2.0\", \"result\" : \"success\", \"id\" : \"id\"}";
    private static final String RESP_ERROR = "{\"jsonrpc\" : \"2.0\", \"error\" : {\"code\": 101, \"message\": \"Failed to open input stream.\"}, \"id\" : \"id\"}";
    public static final String JSON = "application/json";
    public static final int BUF_SIZE = 2 * 1024;
    public static final String FileDir = "uploadfile/";

    private int chunk;
    private int chunks;
    private String name;
    private String user;
    private String time;
    @Inject
    private ImageCurrent imageCurrent;

    /**
     * Handles an HTTP POST request from Plupload.
     *
     * @param req The HTTP request
     * @param resp The HTTP response
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseString = RESP_SUCCESS;
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        System.out.println("BP 1");

        if (isMultipart) {
            System.out.println("BP 2");
            ServletFileUpload upload = new ServletFileUpload();
            try {
                FileItemIterator iter = upload.getItemIterator(req);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    InputStream input = item.openStream();

                    System.out.println("BP 3");
                    // Handle a form field.
                    if (item.isFormField()) {
                        String fileName = item.getFieldName();
                        String value = Streams.asString(input);

                        System.out.println("BP 4");
                        if ("name".equals(fileName)) {
                            this.name = value;
                            System.out.println("BP 5");
                        } else if ("chunks".equals(fileName)) {
                            this.chunks = Integer.parseInt(value);
                            System.out.println("BP 6");
                        } else if ("chunk".equals(fileName)) {
                            this.chunk = Integer.parseInt(value);
                            System.out.println("BP 7");
                        } else if ("user".equals(fileName)) {
                            this.user = value;
                            System.out.println("BP 8");
                        } else if ("time".equals(fileName)) {
                            this.time = value;
                            System.out.println("BP 9");
                        }
                    } // Handle a multi-part MIME encoded file.
                    else {
                        System.out.println("BP 10");
                        String fileDir = req.getSession().getServletContext().getRealPath("/") + FileDir;
                        System.out.println("Dir: " + fileDir);
                        File dstFile = new File(fileDir);
                        if (!dstFile.exists()) {
                            dstFile.mkdirs();
                            System.out.println("BP 10.1");
                        }

                        System.out.println("BP 11");
                        String path = dstFile.getPath() + "/" + this.name;
                        File dst = new File(path);

                        saveUploadFile(input, dst,path);
                        System.out.println("BP 12");
                    }
                }
            } catch (Exception e) {
                responseString = RESP_ERROR;
                e.printStackTrace();
            }
        } // Not a multi-part MIME request.
        else {
            responseString = RESP_ERROR;
            System.out.println("BP 13");
        }

        System.out.println("BP 14");
        if (this.chunk == this.chunks - 1) {
            System.out.println("BP 15");
            System.out.println("Usuario: " + this.user);
            System.out.println("Nombre: " + this.name);
            System.out.println("Hora: " + this.time);
        }

        resp.setContentType(JSON);
        System.out.println("BP 15");
        byte[] responseBytes = responseString.getBytes();
        System.out.println("BP 16");
        resp.setContentLength(responseBytes.length);
        System.out.println("BP 17");
        ServletOutputStream output = resp.getOutputStream();
        System.out.println("BP 18");
        output.write(responseBytes);
        System.out.println("BP 19");
        output.flush();
        System.out.println("BP 20");
        
    }

    /**
     * Saves the given file item (using the given input stream) to the web
     * server's local temp directory.
     *
     * @param input The input stream to read the file from
     * @param dst The dir of upload
     */
    private void saveUploadFile(InputStream input, File dst, String path) throws IOException {
        OutputStream out = null;
        System.out.println("BP 11.1");
        try {
            if (dst.exists()) {
                System.out.println("BP 11.2");
                out = new BufferedOutputStream(new FileOutputStream(dst, true),
                        BUF_SIZE);
            } else {
                System.out.println("BP 11.3");
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        BUF_SIZE);
            }

            System.out.println("BP 11.4");
            byte[] buffer = new byte[BUF_SIZE];
            int len = 0;
            while ((len = input.read(buffer)) > 0) {
                System.out.println("BP 11.5");
                out.write(buffer, 0, len);
            }
            
            imageCurrent.setPath(path);
        } catch (Exception e) {
            System.out.println("BP 11.6");
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    System.out.println("BP 11.7");
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    System.out.println("BP 11.8");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
