package repository;

import model.Person;
import utils.PropertyUtils;
import utils.SQLUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonRepository {
    private final Properties properties;

    public PersonRepository() {
        properties = PropertyUtils.loadProperty();
    }

    private Connection startDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("USERNAME"),
                properties.getProperty("PASSWORD")
        );
    }

    public List<Person> getAllPerson() {
        try (
                Connection connection = startDatabaseConnection();
                Statement statement = connection.createStatement();
        ) {
            var personList = new ArrayList<Person>();
            var rs = statement.executeQuery(SQLUtils.PersonSQL.GELALLPERSON);
            while (rs.next()) {
                personList.add(
                        new Person()
                                .setId(rs.getInt("id"))
                                .setFullName(rs.getString("fullName"))
                                .setEmail(rs.getString("email"))
                                .setAddress(rs.getString("address"))
                                .setGender(rs.getString("gender"))
                );
            }
            return personList;

        } catch (SQLException ex) {
            System.out.println("Failed to retreive all the person data ! ");
            ex.printStackTrace();
        }

        return null;
    }
    public void addAllPersons(List<Person> persons)
    {
        try (Connection connection =startDatabaseConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLUtils.PersonSQL.INSERTNEWPERSON);
            for (Person person : persons) {
                preparedStatement.setString(1, person.getFullName());
                preparedStatement.setString(2, person.getGender());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setString(4, person.getAddress());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Successfully add data to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int addNewPerson(Person person) {
        try (
                Connection connection = startDatabaseConnection();
                PreparedStatement ps = connection.prepareStatement(SQLUtils.PersonSQL.INSERTNEWPERSON);
        ) {
            ps.setString(1, person.getFullName());
            ps.setString(2, person.getGender());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getAddress());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error when adding a new person");
            ex.printStackTrace();
        }
        return 0;
    }

    public int updatePerson(Person updatedPerson) {
        try
                (
                        Connection connection = startDatabaseConnection();
                        PreparedStatement ps = connection.prepareStatement(SQLUtils.PersonSQL.UPDATEPERSON)
                ) {

            ps.setString(1,updatedPerson.getFullName());
            ps.setString(2,updatedPerson.getGender());
            ps.setString(3,updatedPerson.getEmail());
            ps.setString(4,updatedPerson.getAddress());
            ps.setInt(5,updatedPerson.getId());

            return ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }

    }

    public int deletePersonByID(int personID) {
        try (
                Connection connection = startDatabaseConnection();
                PreparedStatement ps = connection.prepareStatement(SQLUtils.PersonSQL.DELETEPERSONBYID)

        ) {
            ps.setInt(1, personID);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Failed to delete the person record with ID = " + personID);
            ex.printStackTrace();
            return 0;
        }

    }


}
