package pwh.springWebStarter.response;

import org.apache.commons.lang3.StringUtils;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;
import pwh.springWebStarter.holder.WebContextHolder;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UnifiedResponseBodyCustomizer extends ResponseBodyCustomizer<UnifiedResponse> {

    @Override
    public Supplier<@NotNull Class<UnifiedResponse>> responseType() {
        return () -> UnifiedResponse.class;
    }

    @Override
    public Supplier<@NotNull UnifiedResponse> initialResponseBody() {
        return () ->
                new UnifiedResponse<>(
                        Instant.now().toEpochMilli(),
                        WebContextHolder.get().getTraceId(),
                        null,
                        null
                );
    }

    @Override
    public BiConsumer<UnifiedResponse, Object> onBodyData() {
        return UnifiedResponse::setData;
    }

    @Override
    public BiConsumer<UnifiedResponse, UnifiedException> onError() {
        return (response, error) -> {
            response.setError(

                    new UnifiedResponse.Error(
                            getUniErrorCode(error).code(),
                            WebContextHolder.get().getHttpRequest().getServletPath(),
                            List.of(error.getMessage()),
                            getUniErrorCode(error).i18n()
                    )
            );
        };
    }


    //根据errorCode查询对应的错误信息
    private static UniErrorCode getUniErrorCode(UnifiedException error){
        try{
            /** 兼容之前的异常情况 **/
            Integer.parseInt(error.getErrorCode());
        }catch (Exception ignore){
            return UniErrorCode.INNTERAL_SYSTEM_ERROR;
        }

        for(UniErrorCode value : UniErrorCode.values()){
            if(StringUtils.equals(error.getErrorCode(),value.code())){
                return value;
            }
        }
        return UniErrorCode.INNTERAL_SYSTEM_ERROR;
    }





    @Override
    public Object getBodyData(UnifiedResponse response) {
        return response.getData();
    }

    @Override
    public Consumer<UnifiedResponse> atFinally() {
        return b -> {
        };
    }
}
