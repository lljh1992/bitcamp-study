package project.util;

public interface Servlet {
  void service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
