package com.projectmanager.model;

import com.itextpdf.text.FontFactory;
import com.projectmanager.util.InventoryUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.projectmanager.entity.Project;
import org.springframework.util.ResourceUtils;

public class TestHibernate {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		try {
/*			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(com.projectmanager.entity.Project.class);
			
			sessionFactory = configuration.configure().buildSessionFactory();
			Project proj = new Project();
			proj.setProjectDesc("first project description..!!");
			proj.setProjectName("Hamsule");

			int projId = new TestHibernate().addProject(proj);
			
			System.out.println("Added a projet with Id : "+projId);*/

			FontFactory.register(ResourceUtils.getFile("classpath:static/fonts/bank_gothic_light_bt.ttf").getAbsolutePath(),"Bank_Gothic");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int addProject(Project proj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int projectId = 99999;
		try {
			tx = session.beginTransaction();
			projectId = (int) session.save(proj);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return projectId;
	}
}
