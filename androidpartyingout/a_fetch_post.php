<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($POST['username']) and isset($_POST['password'])){
        <?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($POST['username']) and isset($_POST['password'])){
        $username = $POST['username'];
        $password = $_POST['password'];




        


        
        $query = "SELECT * FROM usuarios WHERE (name='$username' AND password= '$password') OR
        (correo='$username' AND password= '$password') ";
        $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;

        $lista = array();
        while($row =mysqli_fetch_assoc($result))
        {
            $lista[] = $row;
        }
        $response['lista'] = $lista;
        
            echo json_encode($array);

        }else{
            $response['error'] = true;
            $response['message'] = "No se han encontrado filas, nada a imprimir, asi que voy a detenerme.";
        
        }
        echo json_encode($response);
    }        
   
        
    /*
        if($db->userLogin($_POST['username'], $_POST['password']))){
            $user = $db->getUserByUserName($_POST['username']);
            $response['error'] = false;
            $response['id'] = $user['id'];
            $response['email'] = $user['email'];
            $response['username'] = $user['username'];
  
        
    }*/
    