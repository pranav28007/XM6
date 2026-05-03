<?php
session_start();
include "db.php";

$uname = $_POST['uname'];
$pass = $_POST['pass'];
$fname = $_POST['fname'];

$_SESSION['uname'] = $uname;
$_SESSION['pass'] = $pass;

$sql = "SELECT * FROM users WHERE username='$uname'";
$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0)
{
    $row = mysqli_fetch_assoc($result);

    if($row['password'] == $pass)
    {
        echo "<h2>Welcome " . $row['fullname'] . "</h2>";
    }
    else
    {
        header("Location: mismatch.php");
    }
}
else
{
    $insert = "INSERT INTO users(username, password, fullname) VALUES('$uname','$pass','$fname')";
    mysqli_query($conn, $insert);

    echo "<h2>New User Registered Successfully!</h2>";
    echo "<h3>Welcome $fname</h3>";
}

mysqli_close($conn);
?>
