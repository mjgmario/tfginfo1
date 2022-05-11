<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_local= $_POST['id_local'];
        $query = "SELECT * FROM evento        
        WHERE id_local = '$id_local'
        ORDER BY nombre_evento desc";
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