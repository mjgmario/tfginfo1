<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_local= $_POST['id_local'];
        $query = "SELECT * FROM r_relaciones LEFT OUTER JOIN usuarios ON 
        r_relaciones.id_relacion = usuarios.id
        WHERE  r_relaciones.id_local = '$id_local' ";
        
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