package unit;


import com.edt.common.constant.CommonConstant;
import com.edt.entity.WxMenu;
import com.edt.service.WxService;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@WebAppConfiguration
public class WxServiceTest extends BaseUnitTest{

    @Resource
    private WxService wxService;
    @Test
    public void testCreateMenu(){
        System.out.println(wxService.createMenu(CommonConstant.APPID_DEFAULT,CommonConstant.APPSECRET_DEFAULT,new WxMenu()));
    }

}
