<?php
session_start();
echo "<h2>Password Mismatch!</h2>";
echo "<h3>Username: " . $_SESSION['uname'] . "</h3>";
?>
