<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_seguidor = $_POST['id_seguidor'];
        $id_seguido = $_POST['id_seguido'];
        
        
        $query = "INSERT INTO r_seguidor_seguido(id_seguidor, id_seguido) VALUES 
        ('$id_seguidor', '$id_seguido')";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
           
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se han encontrado filas";
        
        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    