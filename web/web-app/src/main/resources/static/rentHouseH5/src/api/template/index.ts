import http from "@/utils/http";
import type { DictionaryInterfaceRes } from "@/api/template/types";
/**
 * @description 获取数据字典接口
 * @param params
 */
export function getDictionary() {
  return http.get<DictionaryInterfaceRes[]>(
    `/app/apartment/getDetailById?id=9`
  );
}
