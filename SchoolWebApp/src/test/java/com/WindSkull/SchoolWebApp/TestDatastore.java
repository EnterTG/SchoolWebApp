package com.WindSkull.SchoolWebApp;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDatastore {

	/*
	 * @Autowired private Datastore datastore;
	 */
	
	//@Test
	public void testDatastore() {
		/*assertNotNull(datastore);
		testSave();*/
	}
	
	
	public void testSave()
	{
		/*PropertyBox schoolclass = PropertyBox.builder(CLASS)
				.set(NAME, "Test")
				.set(SEMESTER, 1)
				.set(TYPE, DAY.name())
				.set(CREATEYEAR, LocalDate.now().getYear()).build();
		
		OperationResult result = datastore.save(TARGET, schoolclass);
		assertEquals(1, result.getAffectedCount());
		
		Integer classId = result.getInsertedKey(ID).orElse(null); 
		assertEquals(Integer.valueOf(1), classId);
		
		
		PropertyBox schoolclass2 = schoolclass.cloneBox();
		schoolclass.setValue(NAME, "Test2");
		schoolclass.setValue(SEMESTER, 2);
		
		datastore.save(TARGET, schoolclass,DefaultWriteOption.BRING_BACK_GENERATED_IDS);
		Integer productId2 = schoolclass2.getValue(ID); 
		assertEquals(Integer.valueOf(2), productId2);*/
	}
}
