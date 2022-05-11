<?php

    //$mysql = new mysqli('localhost','id18842187_marioivan', 'ia<T(=jQf/T8U//~','id18842187_androidpartyingout');
    $mysql = new mysqli('localhost','root', '', 'androidpartyingout');

 
 if ($mysql->connect_error) {
      die("Failed to connect" . $mysql->connect_error);
}


?>