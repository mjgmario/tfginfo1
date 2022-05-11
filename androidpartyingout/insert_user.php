<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $username = $_POST['nombre'];
        $password = $_POST['password'];
        $correo = $_POST['correo'];
        $publico = $_POST['publico'];
        $phone =  $_POST['phone'];
        
        
        $query1 = "SELECT * FROM usuarios WHERE nombre = '$username'";
        $result1 = $mysql->query($query1);
        if($mysql->affected_rows >0){
            $response['error'] = true;
            $response['message'] = "El nombre ya esta registrado";
        }
        else{
            $query2 = "SELECT * FROM usuarios WHERE correo = '$correo'";
            $result2 = $mysql->query($query2);
            if($mysql->affected_rows >0){
                $response['error'] = true;
                $response['message'] = "El correo ya esta registrado";
            }
            else{
                $query = "INSERT INTO usuarios(nombre, password, correo, publico, phone) VALUES 
            ('$username', '$password', '$correo', '$publico', '$phone' )";

                try {
                    $result = $mysql->query($query);
        
                if($mysql->affected_rows >0){

                $query3 = "SELECT id FROM usuarios         ## ver esto
                WHERE  nombre = '$username' AND 
                correo = '$correo'";
                $result = $mysql->query($query3);                
                $lista = array();
                while($row =mysqli_fetch_assoc($result)){
                    $lista[] = $row;
                }
                    $response['error'] = false;
                    $response['id'] = $lista[0]['id'];
                
                }
                else{
                    $response['error'] = true;
                    $response['message'] = "No se han encontrado filas";
                
                }
                } catch (Exception $e) {
                    $response['error'] = true;
                    $response['message'] = $e->getMessage();
                }
            }
        }
            
            
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    