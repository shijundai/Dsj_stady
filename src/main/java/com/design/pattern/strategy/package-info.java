/**
 * 策略模式(Strategy)
 * 策略模式示例代码
 * JDK中应用:
 *      Collections.sort Arrays.sort中使用的Comparator接口 排序比较接口
 *      ThreadPoolExecutor中使用的RejectedExecutionHandler接口 拒绝策略接口 threadpoolexecutor实现了4种策略
 *                                  AbortPolicy 丢弃任务并抛出RejectedExecutionException异常
 *                                  DiscardPolicy 丢弃任务，但是不抛出异常。
 *                                  DiscardOldestPolicy 丢弃队列最前面的任务，然后重新提交被拒绝的任务。
 *                                  CallerRunsPolicy 由调用线程处理该任务
 *
 *
 *
 */
package com.design.pattern.strategy;