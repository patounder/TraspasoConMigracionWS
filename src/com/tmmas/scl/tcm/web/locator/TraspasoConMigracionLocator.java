package com.tmmas.scl.tcm.web.locator;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;


import org.apache.log4j.Logger;

import com.tmmas.cl.framework20.util.UtilLog;
import com.tmmas.scl.tcm.common.helper.Global;
import com.tmmas.scl.tcm.ejb.session.bean.TraspasoConMigracionBEANRemote;



public class TraspasoConMigracionLocator {

    private final Logger logger = Logger.getLogger(this.getClass());
   // private CompositeConfiguration config;
    private Global globalConfig = new Global();

    public TraspasoConMigracionLocator() {
       UtilLog.setLog(globalConfig.getValorExterno("WEB.log"));
    }


    public TraspasoConMigracionBEANRemote getTraspasoConMigracionBeanRemote() throws Exception {
        logger.debug("Inicio getTraspasoConMigracionBeanRemote");
        Properties properties = new Properties();

        TraspasoConMigracionBEANRemote beanRemote = null;
        try {

            logger.debug(globalConfig.getValorExterno("context.TraspasoConMigracionBEANRemote"));
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, globalConfig.getValorExterno("context.TraspasoConMigracionBEANRemote"));

            logger.debug(globalConfig.getValorExterno("url.TraspasoConMigracionEJB"));
            properties.setProperty(Context.PROVIDER_URL, globalConfig.getValorExterno("url.TraspasoConMigracionEJB"));

            InitialContext initialContext = new InitialContext(properties);

            logger.debug("util.getValor(jndi.TraspasoConMigracionBEAN.Remote:" + globalConfig.getValorExterno("jndi.TraspasoConMigracionBEAN.Remote"));
            beanRemote = (TraspasoConMigracionBEANRemote) initialContext.lookup(globalConfig.getValorExterno("jndi.TraspasoConMigracionBEAN.Remote"));

            
        } catch (Exception e2) {
            e2.printStackTrace();
            logger.error("Error: " + e2.getStackTrace());
            logger.error(e2 + e2.getMessage());
            logger.debug("e2.getMessage: " + e2.getMessage());
        }
        logger.debug("getGestionGarantiasBeanRemote");
        return beanRemote;
    }


   
}
