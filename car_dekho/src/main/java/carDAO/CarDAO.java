package carDAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.internal.build.AllowSysOut;

import com.jspiders.hibernate.car_dekho.CarDTO.CarDTO;

public class CarDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void openConnections() {
		entityManagerFactory = Persistence.createEntityManagerFactory("cardekho");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();

	}

	public static void closeConnections() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();

		}
		if (entityManager != null) {
			entityManager.close();

		}
		if (entityTransaction != null) {

			if (entityTransaction.isActive()) {
				entityTransaction.rollback();

			}
		}
	}

	public static void main(String[] args) {

		boolean condition = true;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter your choice " + "\n1)Insert the car " + "\n2)update the car "
					+ "\n3)delete the car" + "\n4)get the  car information \n5)show all cars record" + "\n6)Exit");
			
			int num = scanner.nextInt();

			switch (num) {
			case 1: {
				try {
					System.out.println("enter car name");
					String name = scanner.next();

					System.out.println("enter car Compony name");
					String componyName = scanner.next();

					System.out.println("enter car price");
					double price = scanner.nextDouble();

					System.out.println("enter car color");
					String color = scanner.next();

					openConnections();
					entityTransaction.begin();

					CarDTO car = new CarDTO();

					// System.out.println("enter car id");

					// int id=scanner.nextInt();

					// car.setId(id);
					car.setName(name);
					car.setComponyName(componyName);
					car.setPrice(price);
					car.setColor(color);

					entityManager.persist(car);
					
					System.out.println("one record inserted");
				
					entityTransaction.commit();
					
				} catch (Exception e) {
					
					System.out.println("Somethin went wrong in inseting car details");
					
				}
				finally {
					
					closeConnections();
					
				}
				
			}

				break;
			case 2: {
				
				System.out.println("enter car id");
				int id = scanner.nextInt();

				System.out.println("enter car name");
				String name = scanner.next();

				System.out.println("enter car Compony name");
				String componyName = scanner.next();

				System.out.println("enter car price");
				double price = scanner.nextDouble();

				System.out.println("enter car color");
				String color = scanner.next();
				
				try {
					

					openConnections();
					entityTransaction.begin();

					CarDTO car = entityManager.find(CarDTO.class, id);
					car.setName(name);
					car.setComponyName(componyName);
					car.setPrice(price);
					car.setColor(color);

					entityManager.persist(car);

					entityTransaction.commit();

					System.out.println("successfully updated");
				} catch (Exception e) {
					System.out.println("something went wrong, record is not updated");
				} finally {

					closeConnections();
				}

			}

				break;
			case 3: {

				System.out.println("enter car id");
				int id = scanner.nextInt();
				try {


					openConnections();
					entityTransaction.begin();

					CarDTO car = entityManager.find(CarDTO.class, id);
					entityManager.remove(car);

					entityTransaction.commit();

					System.out.println("successfully deleted");
				} catch (Exception e) {
					System.out.println("something went wrong, record is not updated");
				} finally {

					closeConnections();
				}

			}
				break;

			case 4: {
				System.out.println("enter car id");
				int id = scanner.nextInt();

				try {

					
					openConnections();
					entityTransaction.begin();

					CarDTO car = entityManager.find(CarDTO.class, id);

					//becouse toString() method is already overridden with the help of @Data annoatin of lombok
					System.out.println(car);

					entityTransaction.commit();

				} catch (Exception e) {
					System.out.println("something went wrong, unable to show you expected record");
				} finally {

					closeConnections();
				}

			}

				break;

			case 5: {

				try {

					openConnections();
					entityTransaction.begin();
					String jpql = "from CarDTO";
					Query query = entityManager.createQuery(jpql);

					 // just for fixing warning about getResltList()
					List<CarDTO> list = query.getResultList();

					for (CarDTO car : list) {
						System.out.println(car);

					}
					entityTransaction.commit();

				} catch (Exception e) {
					System.out.println("something went wrong, unable to show you records");
				} finally {

					closeConnections();
				}

			}
				break;
			case 6: {
				condition = false;
				System.out.println("Thank you!! ,visit again\nFrom Aamer Khan");

			}
				break;
			default: {
				System.out.println("Enter valid Option");
			}

			}
			
		} while (condition);

		scanner.close();

	}
}
