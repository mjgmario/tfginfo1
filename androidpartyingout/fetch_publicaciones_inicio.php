<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id = $_POST['id'];
        $query = "SELECT id, descripcion, fecha FROM publicacion INNER JOIN
        r_seguidor_seguido on publicacion.id_user  = r_seguidor_seguido.id_seguido
        WHERE r_seguidor_seguido.id_seguidor = '$id'
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