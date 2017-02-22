package site.franksite.service.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import site.franksite.pojo.PojoBuilder;


/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/img/fileupload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FileUploadServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8;charset=utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			List<Object> responses = new LinkedList<Object>();
			for (FileItem item : items) {
				if (!item.isFormField()) {
					String path = request.getServletContext().getRealPath("img");
					File folder = new File(path);
					if (!folder.exists()) {
						folder.mkdirs();
					}
					String fileName = item.getName();
					DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					Date now = new Date();
					fileName = format.format(now) + now.getTime() % 1000 + fileName.substring(fileName.lastIndexOf('.'));
					File outputFile = new File(folder, fileName);
					item.write(outputFile);
					String uri = request.getServletPath();
					LOGGER.info(uri);
					if (uri.endsWith("/")) {
						uri = uri.substring(0, uri.length() - 1);
					}
					Object responseObj = PojoBuilder.uploadSuccResponse("../../img/" + fileName);
					responses.add(responseObj);
				}
			}
			
			OutputStream output = response.getOutputStream();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(output, responses.get(0));
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
