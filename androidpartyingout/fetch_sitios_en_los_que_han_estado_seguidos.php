<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    //volver a hacer bien
        $id_user = $_POST['id_user'];
                
        $query = "SELECT * FROM  r_seguidor_seguido  INNER JOIN r_ha_estado_local  ON r_seguidor_seguido.id_seguido = r_ha_estado_local.id_user
        INNER JOIN local ON r_ha_estado_local.id_local = local.id_local 
        WHERE r_seguidor_seguido.id_seguidor = '$id_user'
        ORDER BY fecha desc";
        
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
    