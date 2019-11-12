package com.WindSkull.SchoolWebApp;


import static com.WindSkull.SchoolWebApp.enums.ClassType.DAY;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.CLASS;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.CREATEYEAR;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.ID;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.NAME;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.SEMESTER;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.TARGET;
import static com.WindSkull.SchoolWebApp.models.SchoolClass.TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.property.PropertyBox;

@SpringBootTest
public class TestDatastore {

	@Autowired
	private Datastore datastore;

	
	@Test
	public void testDatastore() {
		testSave();
	}
	
	
	public void testSave()
	{
		PropertyBox schoolclass = PropertyBox.builder(CLASS)
				.set(NAME, "Test")
				.set(SEMESTER, 1)
				.set(TYPE, DAY.name())
				.set(CREATEYEAR, LocalDate.now()).build();
		
		assertNotNull(schoolclass);
		OperationResult result = datastore.save(TARGET, schoolclass);
		assertEquals(1, result.getAffectedCount());
		
		Integer classId = result.getInsertedKey(ID).orElse(null); 
		assertEquals(Integer.valueOf(1), classId);
		
		
		PropertyBox schoolclass2 = schoolclass.cloneBox();
		schoolclass.setValue(NAME, "Test2");
		schoolclass.setValue(SEMESTER, 2);
		
		datastore.save(TARGET, schoolclass,DefaultWriteOption.BRING_BACK_GENERATED_IDS);
		Integer productId2 = schoolclass2.getValue(ID); 
		assertEquals(Integer.valueOf(2), productId2);
	}
}
