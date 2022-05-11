<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_local = $_POST['id_local'];
        $nombre_evento = $_POST['nombre_evento'];
        $fecha = $_POST['fecha'];
        $descripcion =  $_POST['descripcion'];
        $edad_min =  $_POST['edad_min'];
        $edad_max =  $_POST['edad_max'];
        $precio =  $_POST['precio'];
        $precio_copas =  $_POST['precio_copas'];

        $query = "INSERT INTO evento(id_local,nombre_evento,  fecha, descrip, edad_min, 
        edad_max, precio_copas, precio) VALUES 
        ('$id_local' , '$nombre_evento', '$fecha' , '$descripcion', '$edad_min', '$edad_max',
        '$precio_copas', '$precio' )";
          
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
        
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se ha podido ejecutar";
        
        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response); 
   
    }   

?>
