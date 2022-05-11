<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $nombre= $_POST['nombre_local'];
        $query = "SELECT * FROM local
        WHERE  nombre = '$nombre' ";
        
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
            $response['elem'] = $lista[0];
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