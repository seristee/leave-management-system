package org.sce.lms.core.config.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.user.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
@Transactional
public class StringToRolesConverter implements AttributeConverter<String[], List<Authority>> {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private CoreDao coreDao;

    @Override
    public List<Authority> convertToDatabaseColumn(String[] attribute) {
        logger.info("convert to db called");
        List<Authority> authorities = new ArrayList<>();
        for (int i = 0; i < attribute.length; i++) {
            Authority authority = (Authority) coreDao.getObjectById(Authority.class, Long.parseLong(attribute[i]));
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String[] convertToEntityAttribute(List<Authority> dbData) {
        logger.info("convert to list called");
        return dbData.toArray(new String[dbData.size()]);
    }
}
