<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        
                
        $query = "SELECT * FROM puntuacion_local
         ";
        
        try {
            $result = $mysql->query($query);
            
            $lista = array();
            while($row =mysqli_fetch_assoc($result)){
                $lista[] = $row;
            }
            $response['lista'] = $lista;
            
            $response['error'] = false;

        }
         catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    