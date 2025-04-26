package vn.aptech.session2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Name", urlPatterns = {"/file-servlet"})
@MultipartConfig(
        maxFileSize = 3 * 1024 * 1024,
        maxRequestSize = 5 * 1024 * 1024
)
public class FileServlet extends HttpServlet {

    private final static String UPLOAD_DIR = "D:/uploads/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                // TODO upload file
                File uploadPath = new File(UPLOAD_DIR);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }
                List<FileInfo> infoList = new ArrayList<>();
                for (File file : uploadPath.listFiles()) {
                    String name = file.getName();
                    long size = file.length();
                    FileInfo fileInfo = new FileInfo(name, size);
                    infoList.add(fileInfo);
                }
                request.setAttribute("infoList", infoList);
                request.getRequestDispatcher("/WEB-INF/session2/list_files.jsp").forward(request, response);
            } else if (action.equals("upload")) {
                // TODO upload file
                File uploadPath = new File(UPLOAD_DIR);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                request.getParts().forEach(p -> {
                    String fileName = p.getSubmittedFileName();
                    if (fileName != null && !fileName.isEmpty()) {
                        try {
                            p.write(UPLOAD_DIR + fileName);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                });
                response.sendRedirect("/file-servlet");
            } else if (action.equals("download")) {
                String filename = request.getParameter("filename");
                if (filename == null || filename.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing file name");
                    return;
                }

                String filePath = UPLOAD_DIR + filename;
                File file = new File(filePath);
                if (!file.exists()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found with name: " + filename);
                    return;
                }
                response.setContentType(getServletContext().getMimeType(filename));
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                try (FileInputStream inputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int byteRead;
                    while ((byteRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, byteRead);
                    }
                }

            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error process request " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
