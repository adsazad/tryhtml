/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import javafx.scene.control.TextArea;

/**
 *
 * @author arashdeep
 */
public class MakeStructer {

    public void MakeHTML(TextArea Textarea) {
        Textarea.setText("<html>\n"
                + "<head>\n"
                + "<title>HTML Learner</title>\n"
                + "</head>\n"
                + "<body bgcolor=\"red\">\n"
                + "<h1>hello</h1>\n"
                + "</body>\n"
                + "</html>");
    }

    public void MakeStructerJquery(TextArea Textarea) {
        Textarea.setText("<html>\n"
                + "<head>\n"
                + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "<script>\n"
                + "$(document).ready(function(){\n"
                + "    $(\"p\").click(function(){\n"
                + "        $(this).hide();\n"
                + "    });\n"
                + "});\n"
                + "</script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<p>If you click on me, I will disappear.</p>\n"
                + "<p>Click me away!</p>\n"
                + "<p>Click me too!</p>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    public void MakeStructerJavaScript(TextArea Textarea) {
        Textarea.setText("<html>\n"
                + "<body>\n"
                + "\n"
                + "<h2>My First JavaScript</h2>\n"
                + "\n"
                + "<button type=\"button\"\n"
                + "onclick=\"document.getElementById('demo').innerHTML = Date()\">\n"
                + "Click me to display Date and Time.</button>\n"
                + "\n"
                + "<p id=\"demo\"></p>\n"
                + "\n"
                + "</body>\n"
                + "</html> ");
    }

    public void MakeStructerAjax(TextArea Textarea) {
        Textarea.setText("<html>\n"
                + "<body>\n"
                + "\n"
                + "<div id=\"demo\">\n"
                + "<h2>The XMLHttpRequest Object</h2>\n"
                + "<button type=\"button\" onclick=\"loadDoc()\">Change Content</button>\n"
                + "</div>\n"
                + "\n"
                + "<script>\n"
                + "function loadDoc() {\n"
                + "  var xhttp = new XMLHttpRequest();\n"
                + "  xhttp.onreadystatechange = function() {\n"
                + "    if (this.readyState == 4 && this.status == 200) {\n"
                + "      document.getElementById(\"demo\").innerHTML =\n"
                + "      this.responseText;\n"
                + "    }\n"
                + "  };\n"
                + "  xhttp.open(\"GET\", \"ajax_info.txt\", true);\n"
                + "  xhttp.send();\n"
                + "}\n"
                + "</script>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    public void MakeStructerBootStrap3(TextArea Textarea) {
        Textarea.setText("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <title>Bootstrap Example</title>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"jumbotron text-center\">\n"
                + "  <h1>My First Bootstrap Page</h1>\n"
                + "  <p>Resize this responsive page to see the effect!</p> \n"
                + "</div>\n"
                + "  \n"
                + "<div class=\"container\">\n"
                + "  <div class=\"row\">\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 1</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 2</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 3</h3>        \n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    public void MakeStructerBootStrap4(TextArea Textarea) {
        Textarea.setText("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <title>Bootstrap Example</title>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\"></script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"jumbotron text-center\">\n"
                + "  <h1>My First Bootstrap Page</h1>\n"
                + "  <p>Resize this responsive page to see the effect!</p> \n"
                + "</div>\n"
                + "  \n"
                + "<div class=\"container\">\n"
                + "  <div class=\"row\">\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 1</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 2</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 3</h3>        \n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }
}
