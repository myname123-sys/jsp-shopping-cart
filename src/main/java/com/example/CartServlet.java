import jakarta.servlet.*;                 //define the project package
import jakarta.servlet.http.*;           // use for servlet functionallity
import java.io.IOException;              // its needed for servlet functionallity
import java.util.*;                      // used for list and the array list

public class CartServlet extends HttpServlet {              // this line handles http request (GET &POST)

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)        //it is used for when user submit a form
            throws ServletException, IOException {

        String item = request.getParameter("item");              // that reads the items name from the input

        HttpSession session = request.getSession();            // this session stores the data per user

      
        List<String> cart = (List<String>) session.getAttribute("cart");          // that help you to retrive the cart from the session & helps to type casting also. the object is changed to the string

        if (cart == null) {             // if first time user then cart doesn't exist
            cart = new ArrayList<>();           // this lines prevents the cart is empty or null items
        }

       
        if (item != null && !item.trim().isEmpty()) {            
            cart.add(item);
        }

     
        session.setAttribute("cart", cart);            // helps you to update the session data


        response.sendRedirect("cart");            // its redirect to the \cart. It alsdo triggers the doget method
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)       // when the user visits
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");

        request.setAttribute("cartItems", cart);        // passes cart data to the JSP page

        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");            // helps you to send the request to the cart.jsp & JSP will display the cart
        rd.forward(request, response);
    }
}