import { request } from '../request';

/**
 * 获取股票年报数据
 * @param stockCode 股票代码
 */
export function fetchStockFinancialData(stockCode: string) {
  return request<Api.StockDetail.FinancialData>({
    url: 'http://localhost:8080/stock/financial_data',
    method: 'GET',
    params: { stockCode },
    headers: {
      'Content-Type': 'application/json',
    }
  });
}
/**
 * 获取股票实时涨跌数据和预测数据
 *
 * @param stockCode 股票代码
 */
export function fetchStockDataAndPrediction(stockCode: string) {
  return request<Api.StockDetail.DataAndPrediction>({
    url: 'http://localhost:8080/stock',
    method: 'get',
    params: { stockCode },
    headers: {
      'Content-Type': 'application/json',
    }
  });
}
