<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $contenido= $_POST['contenido'];
        $edad= $_POST['edad'];
      
        $precio= $_POST['precio'];

        $query = "SELECT * FROM local
        WHERE  nombre LIKE  '$contenido%' AND Precio < '$precio'
        AND edad_min <= '$edad' AND '$edad' < edad_max  ";
        
      try{  
        $result = $mysql->query($query);
        if($mysql->affected_rows == 0){
            $response['error'] = false;
            $response['hay'] = false;
        }
        else{
    
            $lista = array();
            while($row =mysqli_fetch_assoc($result)){
                $lista[] = $row;
            }
            $response['hay'] = true;
            $response['lista'] = $lista;
            $response['error'] = false;
       }
    }
        catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
    
        $mysql->close();
        echo json_encode($response);
    }
  ?>  