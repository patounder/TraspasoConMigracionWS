package com.tmmas.scl.tcm.ws.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.tmmas.cl.framework.exception.GeneralException;
import com.tmmas.cl.framework20.util.UtilLog;
import com.tmmas.scl.tcm.common.dto.*;
import com.tmmas.scl.tcm.common.helper.*;
import com.tmmas.scl.tcm.ejb.session.bean.TraspasoConMigracionBEANRemote;
import com.tmmas.scl.tcm.web.locator.TraspasoConMigracionLocator;

import org.apache.log4j.Logger;



@WebService
public class TraspasoConMigracionWS {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	private Global globalConfig = new Global();
	private TraspasoConMigracionLocator locator = new TraspasoConMigracionLocator();
	
	
	public TraspasoConMigracionWS(){
		UtilLog.setLog(globalConfig.getValorExterno("WEB.log"));
		
	}
	
    @WebMethod
    public TraspasoConMigracionOUTDTO traspasarConMigracion (TraspasoConMigracionINDTO entrada){
    	logger.info("traspasarConMigracion inicio");
    	TraspasoConMigracionOUTDTO salida = new TraspasoConMigracionOUTDTO();
    	
    	try{
    		Util.imprimirPropiedades(entrada, logger);
   		
    		TraspasoConMigracionBEANRemote remote = locator.getTraspasoConMigracionBeanRemote();
    		
    		salida = remote.traspasarConMigracion(entrada);
    		
    		
    	} catch (GeneralException e) {
            e.printStackTrace();
            salida.setCodigoRetorno(e.getCodigo());
            salida.setNumeroEvento(Long.toString(e.getCodigoEvento()));
            salida.setMensajeRetorno(e.getDescripcionEvento());
        } catch (Exception e) {
            e.printStackTrace();
            salida.setCodigoRetorno("2");
            salida.setNumeroEvento("0");
            logger.info("detalle:" + e.getMessage());
            salida.setMensajeRetorno("Ha ocurrido un error Inesperado, consulte con administrador");
        }
    	
        Util.imprimirPropiedades(salida, logger);
        logger.info("traspasarConMigracion fin");
    	return salida;
    }

}
