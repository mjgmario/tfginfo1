<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_evento= $_POST['id_evento'];
        $query = "SELECT * FROM evento        
        WHERE id_evento = '$id_evento'";
      try{  
        $result = $mysql->query($query);

    
       
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['nombre_evento'] = $lista[0]['nombre_evento'];
        $response['fecha'] = $lista[0]['fecha'];
        $response['descrip'] = $lista[0]['descrip'];
        $response['edad_min'] = $lista[0]['edad_min'];
        $response['edad_max'] = $lista[0]['edad_max'];
        $response['precio_copas'] = $lista[0]['precio_copas'];
        $response['precio'] = $lista[0]['precio'];

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