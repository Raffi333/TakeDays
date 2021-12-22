package tekdays

import grails.transaction.Transactional
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReader
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQueryCreator

import java.util.stream.Collectors

@Transactional
class EnversService {

    SessionFactory sessionFactory

    def getAuditedByRevisionId(Class clazz, int rev) {
        List listOfAudited = AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forEntitiesModifiedAtRevision(clazz, rev).resultList
        def result = listOfAudited?.first()
        return result
    }

    List getAuditedByEntityId(Class clazz, int id) {
        List listOfAudited = AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(clazz, true, true) getResultList()

        List result = listOfAudited?.stream()?.filter({ val -> val.id == id })?.collect(Collectors.toList()) as List
        return result
    }


    List getAllAudited(Class clazz) {
        List listOfAudited = AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(clazz, true, true).getResultList()

        List result = listOfAudited
        return result
    }

    List getAllOnlyExist(Class clazz) {
        List listOfAudited = AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(clazz, true, false).resultList

        List result = listOfAudited
        return result
    }

}
