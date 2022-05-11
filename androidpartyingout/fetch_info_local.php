<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_local= $_POST['id_local'];
        $query = "SELECT * FROM local
        WHERE  id_local = '$id_local' ";
        
      try{  
        $result = $mysql->query($query);

    
       
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['nombre'] = $lista[0]['nombre'];
        $response['descripcion'] = $lista[0]['descripcion'];
        $response['latitud'] = $lista[0]['lat'];
        $response['longitud'] = $lista[0]['longi'];


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