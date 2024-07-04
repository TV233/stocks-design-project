import axios from 'axios';
import { request } from '../request';

/**
 * Login
 *
 * @param username User name
 * @param password Password
 */
export function fetchLogin(username: string, password: string) {
  const data = new URLSearchParams();
  data.append('username', username);
  data.append('password', password);

  return axios({
    method: 'post',
    url: '/auth/login',
    baseURL: 'http://localhost:8080',
    data: data,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

/** Get user info */
export function fetchGetUserInfo() {
  return request<Api.Auth.UserInfo>({
    url: '/user/getUserInfo',
    headers: {
      Authorization: `${localStorage.getItem('jwtToken')}` // Assuming the token is stored in localStorage
    }
  });
}


/**
 * Refresh token
 *
 * @param refreshToken Refresh token
 */
export function fetchRefreshToken(refreshToken: string) {
  return request<Api.Auth.LoginToken>({
    url: '/auth/refreshToken',
    method: 'post',
    data: {
      refreshToken
    }
  });
}

/**
 * return custom backend error
 *
 * @param code error code
 * @param msg error message
 */
export function fetchCustomBackendError(code: string, msg: string) {
  return request({ url: '/auth/error', params: { code, msg } });
}
