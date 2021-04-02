<h1> BOOK LENDING SYSTEM PROJECT </h1>

<h3>Summary:</h3>
<p>This is the project which allow you do CRUD operation for Book, User and lending, returning book. This project contain basic knowledge:</p>
<ul>
<li><b>Java:</b> Flow control, OOP, Collection, Exception handling, I/O, JDBC, JUnit</li>
<li><b>Database:</b> MySQL</li>
<li><b>Pattern:</b> Model - Service - Controller</li>
<li><b>Tool:</b> Eclipse</li>
</ul>

<h3>Prerequisites:</h3>
<p>Import the this project as Maven project, config your database connection information in <i>utils/DBUtils.java</i></p>
<p>When run <i>MainController.java</i> in <i>controller</i> package to start the app, you might want to initialize your database for the first time by choosing <i>"7. Reset database"</i> on the main menu.</p>

<h3>CRUD operation:</h3>
<p>All the input is reading from file, so you may want to change the input files <i>inputfile</i> folder to see the different</p>
<ul><b>Book:</b>
<li>createbook.txt - input bookid::title::author</li>
<li>readbook.txt - input bookid</li>
<li>updatebook.txt - input bookid::title::author</li>
<li>delete.txt - input bookid</li>
<li>lendbook.txt - input bookid::userid</li>
<li>returnbook.txt - input bookid</li>
</ul>

<ul><b>User:</b>
<li>createuser.txt - input userid::firstname::lastname::dateofbirth(with format YYYY-MM-DD)</li>
<li>readuser.txt - input userid</li>
<li>updateuser.txt - input userid::firstname::lastname::dateofbirth</li>
<li>delete.txt - input userid</li>
</ul>

<h3>Print excel file:</h3>
<p>After print, you can find your excel file in the <i>output</i> folder</p>

<h1>Happy coding !</h1>