<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        //devuelve la lista de ids de publicaciones del usuario
        
                
        $query = "SELECT id, descripcion, fecha FROM publicacion
         WHERE fecha >= NOW() + INTERVAL -7 DAY
            AND fecha <  NOW() + INTERVAL  0 DAY 
         ORDER BY n_clicks desc";
        
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
    