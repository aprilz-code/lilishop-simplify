package cn.lili.modules.system.serviceimpl;

import cn.lili.modules.system.entity.dos.SensitiveWords;
import cn.lili.modules.system.mapper.SensitiveWordsMapper;
import cn.lili.modules.system.service.SensitiveWordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 敏感词业务层实现
 *
 * @author Bulbasaur
 * @since 2020/11/17 8:02 下午
 */
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements SensitiveWordsService {

}