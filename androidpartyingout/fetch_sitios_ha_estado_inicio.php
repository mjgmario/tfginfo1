<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_user = $_POST['id_user'];
                
        $query = "SELECT * FROM r_ha_estado_local INNER JOIN local ON r_ha_estado_local.id_local = local.id_local 
         WHERE r_ha_estado_local.id_user = '$id_user'";
        
        try {
            $result = $mysql->query($query);
            
            $lista = array();
            while($row =mysqli_fetch_assoc($result)){
                $lista[] = $row;
            }
            $response['lista'] = $lista;
            
            $response['error'] = false;
            $query2 = "SELECT nombre FROM usuarios
            WHERE id = '$id_user' ";

            $result2 = $mysql->query($query2);
            $lista1 = array();
            while($row =mysqli_fetch_assoc($result2)){
                $lista1[] = $row;
            }
            $response['nombre'] = $lista1[0]['nombre'];

           
        }
         catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    