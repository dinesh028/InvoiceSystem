package com.invoice.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.invoice.dao.TenantDAO;
import com.invoice.model.Tenant;
import com.invoice.service.TenantService;

@Service("tenantserviceimpl")
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantdao;

	public void insertTenantDetails(Tenant tenant) {

		tenantdao.insertTenantDetails(tenant);
	}

	public Tenant getTenantInfo(String tenantId) {
		return tenantdao.getTenantInfo(tenantId);

	}

	public void updateTenantDetails(Tenant tenant) {
		tenantdao.updateTenantDetails(tenant);

	}

	@Override
	public void uploadLogo(CommonsMultipartFile[] fileUpload,
			HttpServletRequest request, Tenant tenant) {

		ServletContext sc = request.getSession().getServletContext();
		String saveDirectory = sc.getRealPath("/") + "/images/";
		try {
			if (fileUpload != null && fileUpload.length > 0) {
				for (CommonsMultipartFile aFile : fileUpload) {

					System.out.println("Saving file: "
							+ aFile.getOriginalFilename());

					if (!aFile.getOriginalFilename().equals("")) {

						aFile.transferTo(new File(saveDirectory
								+ aFile.getOriginalFilename()));

						File oldFile = new File(saveDirectory
								+ aFile.getOriginalFilename());

						// Now invoke the renameTo() method on the reference,
						// oldFile in this case
						deleteOld(saveDirectory+tenant.getTenantId() + ".jpg");
						oldFile.renameTo(new File(saveDirectory
								+ tenant.getTenantId() + ".jpg"));

					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void deleteOld(String fileName) {
		// TODO Auto-generated method stub
	System.out.println(fileName);
		File file = new File(fileName);
		file.delete();
	}
	

}
