<?php 

/* record need to be with keys like database columns. */

function insert_record($record_array)
{

	$sql = "INSERT INTO hotspot VALUES (";

	/* do a loop */
	foreach($record_array as $key => $hotspot)
	{
		$sql .= $hotspot.",";
	}

	$sql = substr($sql,0,-1);
	$sql .= ");";
}

?>