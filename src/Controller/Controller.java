package Controller;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Dificultad;
import Models.UnidadDidactica;



public class Controller implements IController {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private CallableStatement callableStatement = null;
}
