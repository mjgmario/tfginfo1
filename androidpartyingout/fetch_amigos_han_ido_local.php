<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_local= $_POST['id_local'];
        $id_user = $_POST['id_user'];
        $query = "SELECT  id, nombre FROM r_ha_estado_local LEFT OUTER JOIN r_seguidor_seguido ON r_seguidor_seguido.id_seguido =  r_ha_estado_local.id_user
        LEFT OUTER JOIN usuarios ON r_seguidor_seguido.id_seguido =  usuarios.id
        WHERE r_ha_estado_local.id_local = '$id_local' AND r_seguidor_seguido.id_seguidor = '$id_user'
        ORDER BY fecha desc";

      try{  
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